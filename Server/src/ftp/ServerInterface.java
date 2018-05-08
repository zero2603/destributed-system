/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author Pham Anh Thu
 */
public interface ServerInterface extends Remote{
    
    public File getServerFile() throws Exception;
    
    public void mergeServerFile(List<File> files, File dst) throws Exception;
    
    public ArrayList<File> splitServerFile(File src) throws Exception;
    
    public ArrayList<String> getServerFileList(String directory) throws Exception;
    
    public OutputStream getOutputStreamFile(File f) throws Exception;

    public InputStream getInputStreamFile(File f) throws Exception;
    
    public boolean emptyServerList(List<File> files) throws Exception;
    
    public boolean deleteServerFile(String directory) throws Exception;
    
    public File createFilePart(String fileName) throws Exception;
    
    public boolean isFile(String name) throws Exception;
    
    public String getFileName(String directory) throws Exception;
    
    public long getFileSize(String directory) throws Exception;
    
    public long getFileDate(String directory) throws Exception;
    
    public String getFileType(String directory) throws Exception;
    
    public File getFile(String fileName) throws Exception;
}
