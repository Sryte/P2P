package server;

/* A peer is identified by his URL
 * the URL is compsoed by IP:port */

public class Peer {


    private String url;

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

}
