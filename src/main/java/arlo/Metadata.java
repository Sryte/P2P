package arlo;

import java.util.ArrayList;

/* A fileID that designate a specific file is available in 0 to n peers
 * that's why we use a list of peers for a specific file id */

public class Metadata {

    private String fileId;
    private ArrayList<String> listPeers = new ArrayList<String>();

    public Metadata(String fileId, ArrayList<String> listPeers) {
        this.fileId = fileId;
        this.listPeers = listPeers;
    }


    public ArrayList<String> getListPeers() {
        return listPeers;
    }

    public void setListPeers(ArrayList<String> listPeers) {
        this.listPeers = listPeers;
    }

    public String getFileid() {
        return fileId;
    }

    public void setFileid(String fileid) {
        this.fileId = fileid;
    }

}
