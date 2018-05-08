/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import ftp.TransferStatus.Action;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import sun.audio.AudioDataStream;

/**
 *
 * @author Pham Anh Thu
 */
public class Transfer {

    private final int FILE_PART_SIZE = 1024 * 1024; // 1 MB
    private ServerInterface server;
    private Client client;
    private TransferStatus currentTransfer;

    public Transfer(ServerInterface server, Client client) {
        this.server = server;
        this.client = client;
    }

    public TransferStatus getCurrentTransfer() {
        return currentTransfer;
    }
    
    private List<File> splitClientFile(String directory, File src) throws IOException {
        ArrayList<File> list = new ArrayList<>();
        int count = 0;
        String fileName = src.getName();
        
        File dir = new File(directory);
        if(!(dir.exists())) dir.mkdir();

        byte[] temp = new byte[FILE_PART_SIZE];
        FileInputStream fis = new FileInputStream(src);
        BufferedInputStream bis = new BufferedInputStream(fis);
        int byteNums = 0;

        while ((byteNums = bis.read(temp)) > 0) {
            String partName = fileName + "." + count;
            File filePart = new File(directory, partName);
            FileOutputStream fos = new FileOutputStream(filePart);
            fos.write(temp, 0, byteNums);
            list.add(filePart);
            count++;
            fos.close();
        }
        fis.close();
        bis.close();
        return list;
    }

    private void mergeClientFile(List<File> files, File dst) throws IOException {
        FileOutputStream fos = new FileOutputStream(dst);
        byte[] temp = new byte[FILE_PART_SIZE];

        for (File f : files) {
            FileInputStream fis = new FileInputStream(f);
            int i = fis.read(temp);
            if (i >= 0) {
                fos.write(temp, 0, i);
            }
            fis.close();
        }
        fos.close();
    }

    private boolean emptyClientList(List<File> files) {
        for (File f : files) {
            if (!f.delete()) {
                return false;
            }
        }
        return true;
    }

    private void transfer(InputStream is, OutputStream os) throws IOException {
        byte[] temp = new byte[FILE_PART_SIZE];
        int c = 0;
        while ((c = is.read(temp)) >= 0) {
            os.write(temp, 0, c);
        }
        is.close();
        os.close();
    }

    private void process(TransferStatus transferStatus) throws Exception {
        File clientFilePart = null;
        File serverFilePart = null;
        if (transferStatus.getAction() == TransferStatus.Action.UPLOAD) {
            do {
                if (transferStatus.getStatus() == TransferStatus.Status.PAUSE) {
                    return; // PAUSE
                }
                clientFilePart = transferStatus.getNextFilePart();
                if (clientFilePart != null) {
                    System.out.println(clientFilePart.getName());
                    serverFilePart = this.server.createFilePart(clientFilePart.getName());
                    InputStream is = new FileInputStream(clientFilePart);
                    OutputStream os = this.server.getOutputStreamFile(serverFilePart);
                    transfer(is, os);
                    transferStatus.addTranferredFileParts(serverFilePart);
                    client.updateStatus("Uploading file " + transferStatus.getDestinationFile().getCanonicalPath() + " : part"
                        + transferStatus.getNumsOfTranferredPart()
                        + "/" + transferStatus.getNumsOfPart());
                } else {
                    // merge file
                    this.server.mergeServerFile(transferStatus.getTranferredFileParts(),
                            transferStatus.getDestinationFile());
                }
            } while (clientFilePart != null);
//            List<File> delList = transferStatus.getTranferredFileParts();
            this.server.emptyServerList(transferStatus.getTranferredFileParts());
            client.resetGUI();
            client.updateStatus("Upload Complete!");
        } else { // DOWNLOAD
            do {
                if (transferStatus.getStatus() == TransferStatus.Status.PAUSE) {
                    return;
                }
                serverFilePart = transferStatus.getNextFilePart();
                if (serverFilePart != null) {
                    clientFilePart = new File("client", serverFilePart.getName());
                    InputStream is = this.server.getInputStreamFile(serverFilePart);
                    OutputStream os = new FileOutputStream(clientFilePart);
                    transfer(is, os);
                    transferStatus.addTranferredFileParts(clientFilePart);
                    client.updateStatus("Download to " + transferStatus.getDestinationFile().getPath() + ": "
                        + transferStatus.getNumsOfTranferredPart()
                        + "/" + transferStatus.getNumsOfPart());
                } else {
                    this.mergeClientFile(transferStatus.getTranferredFileParts(),
                            transferStatus.getDestinationFile());
                }
            } while (serverFilePart != null);
//            List<File> delList = transferStatus.getTranferredFileParts();
            emptyClientList(transferStatus.getTranferredFileParts());
            client.resetGUI();
            client.updateStatus("Download Complete!");
        }
    }

    public void processThread(TransferStatus transferStatus) {
        // override run method by lambda expression
        Thread thread = new Thread(() -> {
            try {
                this.process(transferStatus);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        thread.start();
    }

    public void upload(File src, File dst) throws IOException {
        List<File> clientFileParts = this.splitClientFile("client", src);
        this.currentTransfer = new TransferStatus(Action.UPLOAD, clientFileParts, src, dst);
        this.processThread(currentTransfer);
    }

    public void download(File src, File dst) throws Exception {
        List<File> serverFileParts = server.splitServerFile(src);
        this.currentTransfer = new TransferStatus(Action.DOWNLOAD, serverFileParts, src, dst);
        this.processThread(currentTransfer);
    }
}
