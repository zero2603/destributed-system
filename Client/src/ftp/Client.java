/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Pham Anh Thu
 */
public class Client extends javax.swing.JFrame {

    public ServerInterface server;
    public String clientRootDir;
    public String currentClientDir;
    public String currentClientFile;
    public String currentServerDir;
    public String currentServerFile;
    public ArrayList<String> clientList;
    public ArrayList<String> serverList;
    public Transfer transfer;
    public FileSystemView fileSystemView;

    /**
     * Creates new form Client
     */
    public Client() {
        this.fileSystemView = FileSystemView.getFileSystemView();
        this.currentClientDir = "";
        this.currentClientFile = "";
        this.currentServerDir = "";
        this.currentServerFile = "";
        initComponents();
        this.clientRootDir = pathClient.getText();
        this.btnResume.setEnabled(false);
        this.btnPause.setEnabled(false);
        this.btnStop.setEnabled(false);
        updateClientDir();
        try {
            updateServerDir();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    Client(String clientPath, ServerInterface server) {
        this.fileSystemView = FileSystemView.getFileSystemView();
        this.currentClientDir = "";
        this.currentClientFile = "";
        this.currentServerDir = "";
        this.currentServerFile = "";
        this.server = server;
        initComponents();
        pathClient.setText(clientPath);
        this.clientRootDir = clientPath;
        this.btnResume.setEnabled(false);
        this.btnPause.setEnabled(false);
        this.btnStop.setEnabled(false);
        this.transfer = new Transfer(this.server, this);
        updateClientDir();
        try {
            updateServerDir();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inputServer = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pathClient = new javax.swing.JTextField();
        btnChoose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClient = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblServer = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtStatus = new javax.swing.JTextArea();
        btnPause = new javax.swing.JButton();
        btnResume = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();
        btnDeleteClient = new javax.swing.JButton();
        btnDownload = new javax.swing.JButton();
        btnDeleteServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Server");

        inputServer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputServer.setText("localhost:1099");
        inputServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputServerActionPerformed(evt);
            }
        });

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refesh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Client Path");

        pathClient.setEditable(false);
        pathClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathClientActionPerformed(evt);
            }
        });

        btnChoose.setText("Choose Folder");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        tblClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Size", "Last Modified"
            }
        ));
        tblClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClient);

        tblServer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Size", "Last Modified"
            }
        ));
        tblServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblServer);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("CLIENT FOLDER");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("SERVER FOLDER");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("STATUS");

        txtStatus.setEditable(false);
        txtStatus.setColumns(20);
        txtStatus.setRows(5);
        jScrollPane3.setViewportView(txtStatus);

        btnPause.setText("PAUSE");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnResume.setText("RESUME");
        btnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumeActionPerformed(evt);
            }
        });

        btnStop.setText("STOP");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnDeleteClient.setText("Delete");
        btnDeleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClientActionPerformed(evt);
            }
        });

        btnDownload.setText("Download");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        btnDeleteServer.setText("Delete");
        btnDeleteServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnResume, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 23, Short.MAX_VALUE)
                                .addComponent(inputServer, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(pathClient)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChoose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUpload)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeleteClient))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDownload)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeleteServer))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(446, 446, 446))
                                .addComponent(jScrollPane2)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputServer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChoose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(pathClient, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpload)
                    .addComponent(btnDeleteClient)
                    .addComponent(btnDownload)
                    .addComponent(btnDeleteServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPause)
                        .addGap(18, 18, 18)
                        .addComponent(btnResume)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStop)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputServerActionPerformed

    private void pathClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathClientActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        try {
            if (inputServer.getText().equals("")) {
                JOptionPane.showMessageDialog(btnConnect, "You must fill IP and port server", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (connect()) {
                open();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        updateClientDir();
        if(server != null) {
            try {
                updateServerDir();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        chooseClientFolder();
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        TransferStatus transferStatus = this.transfer.getCurrentTransfer();
        if (transferStatus.getStatus() == TransferStatus.Status.TRANSFERING) {
            transferStatus.pause();
        }
        btnPause.setEnabled(false);
        btnResume.setEnabled(true);
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        if (server == null) {
            return;
        }
        updateCurrentClientFile();
        if (currentClientFile.equals("") || (new File(clientRootDir + "/" + currentClientDir + "/" + currentClientFile).isFile() == false)) {
            JOptionPane.showMessageDialog(this, "File not selected.");
            return;
        }
        // check exist file name
        boolean exist = false;
        try {
            updateServerDir();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < serverList.size(); i++) {
            if (currentClientFile.equals(serverList.get(i))) {
                exist = true;
                break;
            }
        }

        String dstName = currentClientFile;
        if (exist) {
            int confirm = JOptionPane.showConfirmDialog(this, "The file \"" + currentClientFile + "\" already exists, Do you want to override it?",
                    "alert", JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // ghi đè
                    this.server.deleteServerFile(currentServerDir + currentClientFile);
                    this.btnResume.setEnabled(false);
                    this.btnPause.setEnabled(true);
                    this.btnStop.setEnabled(true);
                    btnUpload.setEnabled(false);
                    btnDownload.setEnabled(false);
                    File dst = null;
                    try {
                        dst = server.getFile(dstName);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    File src = new File(clientRootDir + "\\" + currentClientDir + "\\" + currentClientFile);
                    this.transfer.upload(src, dst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                return;
            }
        } else {
            this.btnResume.setEnabled(false);
            this.btnPause.setEnabled(true);
            this.btnStop.setEnabled(true);
            btnUpload.setEnabled(false);
            btnDownload.setEnabled(false);
            File dst = null;
            try {
                dst = server.getFile(dstName);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            File src = new File(clientRootDir + "\\" + currentClientDir + "\\" + currentClientFile);
            try {
                this.transfer.upload(src, dst);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        if (server == null) {
            return;
        }
        updateCurrentServerFile();
        try {
            if (currentServerFile.equals("") || (server.isFile(currentServerDir + "/" + currentServerFile) == false)) {
                JOptionPane.showMessageDialog(this, "File not selected.");
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // check exist file name
        boolean exist = false;
        updateClientDir();
        for (int i = 0; i < clientList.size(); i++) {
            if (currentServerFile.equals(clientList.get(i))) {
                exist = true;
                break;
            }
        }
        String dstName = currentServerFile;
        if (exist == true) {
            int confirm = JOptionPane.showConfirmDialog(this, "The file \"" + currentServerFile + "\" already exists, Do you want to override it?",
                    "alert", JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // ghi đè
                    new File(clientRootDir + "\\" + currentClientDir + "\\" + currentServerFile).delete();
                    this.btnResume.setEnabled(false);
                    this.btnPause.setEnabled(true);
                    this.btnStop.setEnabled(true);
                    btnDownload.setEnabled(false);
                    btnUpload.setEnabled(false);
                    File dst = new File(clientRootDir + "\\" + currentClientDir + "\\" + dstName);
                    File src = null;
                    try {
                        src = this.server.getFile(currentServerFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(currentServerFile);

                    this.transfer.download(src, dst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                return;
            }
        } else {
            this.btnResume.setEnabled(false);
            this.btnPause.setEnabled(true);
            this.btnStop.setEnabled(true);
            btnDownload.setEnabled(false);
            btnUpload.setEnabled(false);
            File dst = new File(clientRootDir + "\\" + currentClientDir + "\\" + dstName);
            File src = null;
            try {
                src = this.server.getFile(currentServerFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(currentServerFile);
            try {
                this.transfer.download(src, dst);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnDeleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClientActionPerformed
        updateCurrentClientFile();
        if (currentClientFile.equals("..")) {
            return;
        }
        new File(clientRootDir + "\\" + currentClientDir + "\\" + currentClientFile).delete();
        updateClientDir();
    }//GEN-LAST:event_btnDeleteClientActionPerformed

    private void btnDeleteServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteServerActionPerformed
        try {
            updateCurrentServerFile();
            this.server.deleteServerFile(currentServerDir + currentServerFile);
            updateServerDir();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteServerActionPerformed

    private void btnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumeActionPerformed
        TransferStatus transferStatus = this.transfer.getCurrentTransfer();
        
        if (transferStatus.getStatus() == TransferStatus.Status.PAUSE) {
            transferStatus.resume();
            this.transfer.processThread(transferStatus);
            btnResume.setEnabled(false);
            btnPause.setEnabled(true);
        }
    }//GEN-LAST:event_btnResumeActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        TransferStatus transferStatus = this.transfer.getCurrentTransfer();
        if (transferStatus.getStatus() == TransferStatus.Status.TRANSFERING) {
            transferStatus.pause();
        }
        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        try {
            server.emptyServerList(transferStatus.getTranferredFileParts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.resetGUI();
    }//GEN-LAST:event_btnStopActionPerformed

    private void tblClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientMouseClicked
        int index = tblClient.getSelectedRow();
        String name = (String) tblClient.getModel().getValueAt(index, 0);

        currentClientFile = "";
        if (evt.getClickCount() >= 2) {
            if (new File(clientRootDir + "/" + currentClientDir + "/" + name).isFile() == true) {
                return;
            }
            if (name.equals("..")) {
                if (currentClientDir.lastIndexOf("/") != -1) {
                    System.out.println(currentClientDir);
                    currentClientDir = currentClientDir.substring(0, currentClientDir.lastIndexOf("/"));
                    System.out.println(currentClientDir);
                } else {
                    currentClientDir = "";
                }
            } else {
                if (!"".equals(currentClientDir)) {
                    currentClientDir = currentClientDir.concat("/").concat(name);
                } else {
                    currentClientDir = currentClientDir.concat(name);
                }
            }
            updateClientDir();
            System.out.println(currentClientDir);
        }
    }//GEN-LAST:event_tblClientMouseClicked

    private void tblServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServerMouseClicked
        if (server == null) {
            return;
        }
        int index = tblServer.getSelectedRow();
        String name = (String) tblServer.getModel().getValueAt(index, 0);

        if (evt.getClickCount() >= 2) {

            try {
                if (server.isFile(currentServerDir + "/" + name) == true) {
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if ("..".equals(name)) {

                if (currentServerDir.lastIndexOf("/") != -1) {
                    currentServerDir = currentServerDir.substring(0, currentServerDir.lastIndexOf("/"));

                } else {
                    currentServerDir = "";
                }

            } else {

                if (!"".equals(currentServerDir)) {
                    currentServerDir = currentServerDir.concat("/").concat(name);
                } else {
                    currentServerDir = currentServerDir.concat(name);
                }
            }
            try {
                updateServerDir();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(currentServerDir);
        }
    }//GEN-LAST:event_tblServerMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
    //khai bao ham

    /**
     * Hien thi status len giao dien GUI
     *
     * @param str
     */
    public void updateStatus(String str) {
        txtStatus.setVisible(true);
        txtStatus.append(str + "\n");
    }

    /**
     * Ket noi toi server
     *
     * @return true neu ket noi thanh cong
     * @throws Exception
     */
    private boolean connect() throws Exception {
        updateStatus("Connecting to server ...." + inputServer.getText() + "\n");
        String url = "rmi://" + inputServer.getText() + "/server";
        updateStatus(url);
        this.server = (ServerInterface) Naming.lookup(url);
        if (this.server != null) {
            return true;
        } else {
            return false;
        }
    }

    private void open() throws Exception {
        String fileClientPath = System.getProperty("user.home");
        updateStatus(fileClientPath);
        Client t = new Client(fileClientPath, this.server);
        t.setVisible(true);
        this.setVisible(false);
    }

    public void updateClientDir() {
        updateStatus(clientRootDir);
        File file = new File(clientRootDir + "/" + currentClientDir);
        if (!file.isDirectory()) {
            currentClientDir = "";
            file = new File(clientRootDir + "/" + currentClientDir);
        }
        clientList = new ArrayList<>(Arrays.asList(file.list()));
        if (!currentClientDir.equals("")) {
            clientList.add(0, "..");
        }
        AbstractTableModel tableModel = new AbstractTableModel() {
            String[] columnName = {"Name", "Type", "Size", "Last Modified"};

            @Override
            public int getRowCount() {
                return clientList.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                switch (columnIndex) {
                    case 0:
                        return clientList.get(rowIndex);
                    case 1:
                        return fileSystemView.getSystemTypeDescription(new File(clientRootDir + "/" + currentClientDir + "/" + clientList.get(rowIndex)));
                    case 2: {
                        long length = new File(clientRootDir + "/" + currentClientDir + "/" + clientList.get(rowIndex)).length();
                        if (length >= 1024) {
                            length = length / 1024; // doi sang KB
                        }
                        return String.valueOf(length).concat(" KB");
                    }
                    case 3:
                        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new File(clientRootDir + "/" + currentClientDir + "/" + clientList.get(rowIndex)).lastModified());
                }
                return null;
            }
        };
        tblClient.setModel(tableModel);
        // dat chieu rong cho cot name
        TableColumnModel tcm = tblClient.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setMinWidth(300);
    }
    
    public void updateServerDir() throws Exception {
        if(server == null) return;
        serverList = server.getServerFileList(currentServerDir);
        if(currentServerDir.length() != 0) 
            serverList.add(0, "..");
        
        AbstractTableModel tableModel = new AbstractTableModel() {
            String[] columnName = {"Name", "Type", "Size", "Last Modified"};

            @Override
            public int getRowCount() {
                return serverList.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                switch (columnIndex) {
                    case 0:
                        return serverList.get(rowIndex);
                    case 1: {
                        try {
                            return server.getFileType(currentServerDir + "/" + serverList.get(rowIndex));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    case 2: {
                        long length = 0;
                        try {
                            length = server.getFileSize(currentServerDir + "/" + serverList.get(rowIndex));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (length >= 1024) {
                            length = length / 1024; // doi sang KB
                        }
                        return String.valueOf(length).concat(" KB");
                    }
                    case 3: {
                        try {
                            return new SimpleDateFormat("MM/dd/yyyy").format(server.getFileDate(currentServerDir +  "/" + serverList.get(rowIndex)));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                return null;
            }
        };
        tblServer.setModel(tableModel);
        // dat chieu rong cho cot name
        TableColumnModel tcm = tblServer.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setMinWidth(300);
    }
    
    public void chooseClientFolder() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Choose one folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(true);
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            pathClient.setText(jfc.getSelectedFile().toString());
            clientRootDir = pathClient.getText();
            currentClientDir = ""; // chua chon
            updateClientDir();
        }
    }
    
    public void updateCurrentClientFile() {
        int index = tblClient.getSelectedRow();
        if(index != -1)
            currentClientFile = (String) tblClient.getModel().getValueAt(index, 0);
        else currentClientFile = "";
    }
    
    public void updateCurrentServerFile() {
        int index = tblServer.getSelectedRow();
        if(index != -1)
            currentServerFile = (String) tblServer.getModel().getValueAt(index, 0);
        else currentServerFile = "";
    }
    
    public void resetGUI() {
        btnResume.setEnabled(false);
        btnPause.setEnabled(false);
        btnStop.setEnabled(false);
        btnUpload.setEnabled(true);
        btnDownload.setEnabled(true);
//        txtStatus.setText("");
        updateClientDir();
        if (server != null) {
            try {
                updateServerDir();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDeleteClient;
    private javax.swing.JButton btnDeleteServer;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnResume;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnUpload;
    private javax.swing.JTextField inputServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField pathClient;
    private javax.swing.JTable tblClient;
    private javax.swing.JTable tblServer;
    private javax.swing.JTextArea txtStatus;
    // End of variables declaration//GEN-END:variables
}
