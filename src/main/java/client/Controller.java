package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.FileReader;
import java.util.Base64;

public class Controller {

    public void uploadFilesData(String url, String name, String fileId) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        FileReader fr = new FileReader("C:\\Users\\laure\\Desktop\\P2P\\" + name); //change to relative path
        BufferedReader br = new BufferedReader(fr);

        String reader;
        String fileContent = null;

        while ((reader = br.readLine()) != null) {
            fileContent+=reader;
        }

        url = "http://"+url+"/files/"+fileId;
        HttpPost postFileData = new HttpPost(url);

        String payload = "{\"fileContent\":\""+fileContent+"\"}";
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

        postFileData.setEntity(entity);

        HttpResponse response = httpClient.execute(postFileData);

        System.out.println(response.getStatusLine().getStatusCode());

        br.close();
        fr.close();
    } // server function missing

    public void uploadFilesMetadata(String url, String fileId, long size, String name) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/files";
        HttpPost postFileMetadata = new HttpPost(url);

        String payload = "{\"fileId\":\""+fileId+"\",\"size\":\""+size+"\",\"name\":\""+name+"\"}";
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

        postFileMetadata.setEntity(entity);

        HttpResponse response = httpClient.execute(postFileMetadata);

        System.out.println(response.getStatusLine().getStatusCode());
    }

    public void registerPeers(String url, String myURL) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/peers";
        HttpPost postPeer = new HttpPost(url);

        String payload = "{\"url\":\""+myURL+"\"}";
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

        postPeer.setEntity(entity);

        HttpResponse response = httpClient.execute(postPeer);

        System.out.println(response.getStatusLine().getStatusCode());
    }

    public void getFilesData(String url, String fileId) throws Exception { //Is OK
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/files/"+fileId;
        HttpGet getFile = new HttpGet(url);

        HttpResponse response = httpClient.execute(getFile);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String reader;
        String content="";
        while ((reader = br.readLine()) != null) {
            content+= reader;
        }

        String fileContent;
        fileContent = new String (Base64.getDecoder().decode(content));

        System.out.println(fileContent);
        System.out.println(response.getStatusLine().getStatusCode());

        br.close();
    }

    public void getMetadata(String url) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/files";
        HttpGet getMeta = new HttpGet(url);

        HttpResponse response = httpClient.execute(getMeta);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        System.out.println(response.getStatusLine().getStatusCode());

        br.close();
    }

    public void listPeers(String url) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/peers";
        HttpGet getPeers = new HttpGet(url);

        HttpResponse response = httpClient.execute(getPeers);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        System.out.println(response.getStatusLine().getStatusCode());

        br.close();
    }

    public void unregisterPeers(String url, String peerId) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/peers/"+peerId;
        HttpDelete deletePeer = new HttpDelete(url);

        HttpResponse response = httpClient.execute(deletePeer);

        System.out.println(response.getStatusLine().getStatusCode());
    }

    public void deleteFilesData(String url, String fileId) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/files/"+fileId;
        HttpDelete deleteFile = new HttpDelete(url);

        HttpResponse response = httpClient.execute(deleteFile);

        System.out.println(response.getStatusLine().getStatusCode());
    } // server function missing

}