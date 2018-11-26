package arlo;

/* a file is composed of a fileID and a peerID */

public class FileMetadata {

    private String fileId;
    private String peerId;

    public String getPeerId() {
        return peerId;
    }

    public void setPeerID(String peerId) {
        this.peerId = peerId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

}
