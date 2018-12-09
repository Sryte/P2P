package tools;

/* A fileID that designate a specific file is available in 0 to n peers
 * that's why we use a list of peers for a specific file id */

public class Metadata {

    private String fileId;
    private long size;
    private String name;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
