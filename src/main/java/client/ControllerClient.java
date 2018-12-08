package client;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ControllerClient {

    public void uploadFilesData(String url, String name, String fileId) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        FileReader fr = new FileReader(System.getProperty("user.dir")+"\\share\\" + name); //change to relative path
        BufferedReader br = new BufferedReader(fr);

        String reader;
        String fileContent = null;

        while ((reader = br.readLine()) != null) {
            fileContent+=reader;
        }

        url = "http://"+url+"/files/"+fileId;
        HttpPost postFileData = new HttpPost(url);

        String payload = "{\"content\":\""+fileContent+"\"}";
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

        postFileData.setEntity(entity);

        HttpResponse response = httpClient.execute(postFileData);

        System.out.println(response.getStatusLine().getStatusCode());

        br.close();
        fr.close();
    }

    public void uploadFilesMetadata(String url, String name) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        url = "http://"+url+"/files";
        HttpPost postFileMetadata = new HttpPost(url);

        String path = System.getProperty("user.dir")+"\\share\\" + name;
        File file = new File(path);
        long size = file.length();
        String size2 = new String(String.valueOf(size));

        long fileId = name.hashCode() * size2.hashCode();

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

        //System.out.println(fileContent);
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
    }

}