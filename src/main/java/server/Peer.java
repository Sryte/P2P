package server;

/* A peer is identified by his URL
 * the URL is compsoed by IP:port */

public class Peer {
    private String url;

    public Peer() {
    }

    public Peer(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Peer{" +
                "url='" + url + '\'' +
                '}';
    }
}
