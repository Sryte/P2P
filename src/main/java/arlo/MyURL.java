package arlo;

import java.net.DatagramSocket;
import java.net.InetAddress;

/* This class gives us our own ip adress
 * we use it to define the url of each server : IP:port
  * the combination IP:port is also used to identify a peer*/

public class MyURL {

    private String url;

    public MyURL() {
        String ip;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            url = ip + ":8080";
        }
        catch (Exception e) { url = ""; }
    }

    public String getURL() {
        return url;
    }
}
