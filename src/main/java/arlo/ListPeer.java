package arlo;

import java.util.ArrayList;

/* class used by the server to send back to the client the list of peers and a message with it*/

public class ListPeer {

    private ArrayList<String> listPeers = new ArrayList<String>();

    private String message;

    public ListPeer(ArrayList<String> listPeers, String message) {
        this.listPeers = listPeers;
        this.message = message;
    }

    public ArrayList<String> getListPeers() {
        return listPeers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setListPeers(ArrayList<String> listPeers) {
        this.listPeers = listPeers;
    }
}
