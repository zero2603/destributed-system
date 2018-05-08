/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pham Anh Thu
 */
public class TransferStatus {
    public enum Status {
        TRANSFERING, PAUSE, DONE
    };
    
    public enum Action {
        UPLOAD, DOWNLOAD
    };
    
    private Action action;
    private Status status;
    private int pendingPosition;
    List<File> fileParts;
    List<File> tranferredFileParts;
    File src, dst;

    public TransferStatus(Action action, List<File> fileParts, File src, File dst) {
        this.action = action;
        this.status = Status.TRANSFERING;
        this.pendingPosition = 0;
        this.fileParts = fileParts;
        this.tranferredFileParts = new ArrayList<File>();
        this.src = src;
        this.dst = dst;
    }
    
    public void pause() {
        this.status = Status.PAUSE;
    }
    
    public void resume() {
        this.status = Status.TRANSFERING;
    }
    
    public File getNextFilePart() {
        if(this.pendingPosition == this.fileParts.size()) {
            this.status = Status.DONE;
            return null;
        }
        File f = fileParts.get(pendingPosition);
        pendingPosition++;
        return f;
    }
    
    public File getDestinationFile() {
        return this.dst;
    }

    public Action getAction() {
        return action;
    }

    public Status getStatus() {
        return status;
    }

    public List<File> getTranferredFileParts() {
        return tranferredFileParts;
    }

    public File getSrc() {
        return src;
    }
    
    public void addTranferredFileParts(File f) {
        this.tranferredFileParts.add(f);
    }
    
    public int getNumsOfPart() {
        return this.fileParts.size();
    }
    
    public int getNumsOfTranferredPart() {
        return this.tranferredFileParts.size();
    }
}
