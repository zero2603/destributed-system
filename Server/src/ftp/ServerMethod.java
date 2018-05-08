/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import com.healthmarketscience.rmiio.SerializableInputStream;
import com.healthmarketscience.rmiio.SerializableOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Pham Anh Thu
 */
public class ServerMethod extends UnicastRemoteObject implements ServerInterface{
    private final String serverDirectory;
    private final FileSystemView fileSystemView;
    private final int FILE_PART_SIZE = 1024 * 1024; // 1MB
    private File serverFile;

    public ServerMethod(File serverFile) throws RemoteException {
        super();
        this.serverDirectory = serverFile.getAbsolutePath();
        this.fileSystemView = FileSystemView.getFileSystemView();
        this.serverFile = serverFile;
    }
    
    
    @Override
    public File getServerFile() throws Exception {
        return serverFile;
    }

    @Override
    public void mergeServerFile(List<File> files, File dst) throws Exception {
        FileOutputStream fos = new FileOutputStream(dst);
        byte[] temp = new byte[FILE_PART_SIZE];
        
        for(File f: files) {
            FileInputStream fis = new FileInputStream(f);
            int i = fis.read(temp);
            if(i >= 0) fos.write(temp, 0, i);
            fis.close();
        }
        fos.close();
    }

    @Override
    public ArrayList<File> splitServerFile(File src) throws Exception {
        ArrayList<File> list = new ArrayList<>();
        int count = 0;
        String fileName = src.getName();
        
        byte[] temp = new byte[FILE_PART_SIZE];
        FileInputStream fis = new FileInputStream(src);
        BufferedInputStream bis = new BufferedInputStream(fis);
        int byteNums = 0;
        
        while((byteNums = bis.read(temp)) > 0) {
            String partName = fileName + "." + count;
            File filePart = new File("server_tmp", partName);
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

    @Override
    public ArrayList<String> getServerFileList(String directory) throws Exception {
        File current = new File(serverDirectory + "/" + directory);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(current.list()));
        return list;
    }

    @Override
    public OutputStream getOutputStreamFile(File f) throws Exception {
        return new SerializableOutputStream(
                new FileOutputStream(f));
    }

    @Override
    public InputStream getInputStreamFile(File f) throws Exception {
        return new SerializableInputStream(
                new FileInputStream(f));
    }

    @Override
    public boolean emptyServerList(List<File> files) throws Exception {
        for(File f: files) {
            if(!f.delete()) return false;
        }
        return true;
    }

    @Override
    public boolean deleteServerFile(String directory) throws Exception {
        return new File(serverDirectory + "/" + directory).delete();
    }

    @Override
    public File createFilePart(String fileName) throws Exception {
        File temp = new File("server_tmp");
        if(!temp.exists()) temp.mkdir();
        return new File("server_tmp", fileName);
    }

    @Override
    public boolean isFile(String name) throws Exception {
        File f = new File(serverDirectory + "/" + name);
        if(f.exists()) return f.isFile();
        else return false;
    }

    @Override
    public String getFileName(String directory) throws Exception {
        File f = new File(serverDirectory + "/" + directory);
        return f.getName();
    }

    @Override
    public long getFileSize(String directory) throws Exception {
        File f = new File(serverDirectory + "/" + directory);
        return f.length();
    }

    @Override
    public long getFileDate(String directory) throws Exception {
        File f = new File(serverDirectory + "/" + directory);
        return f.lastModified();
    }

    @Override
    public String getFileType(String directory) throws Exception {
        File f = new File(serverDirectory + "/" + directory);
        return fileSystemView.getSystemTypeDescription(f);
    }
 
    @Override
    public File getFile(String fileName) throws Exception {
        File f = new File(serverDirectory + "/" + fileName);
        return f;
    }
}

