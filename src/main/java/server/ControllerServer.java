package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.springframework.web.bind.annotation.*;
import tools.FileDelete;
import tools.FileReader;
import tools.StringWriter;

@RestController
public class ControllerServer {

    private final List<String> listPeers = new ArrayList<>();

    /* The metadata list is a hashmap to be able to attribute keys to files and peers */
    private final HashMap<String,Metadata> mapperMetadata = new HashMap<>();

    // public final MyURL myURL = new MyURL();

    /* adding a specific peer to the list of peers of the network */
    @RequestMapping(value = "/peers", method = RequestMethod.POST)
    public void register(@RequestBody Peer peer) {
        if(!listPeers.contains(peer.getURL()))
            listPeers.add(peer.getURL());
    }

    /* Obtain the list of available peers in the network and checking if the list is empty*/
    @RequestMapping(value = "/peers", method = RequestMethod.GET)
    public List<String> getListPeers()
    {
        if (listPeers.isEmpty())
            return null;

        return listPeers;
    }

    /* Delete a specific peer in the list of the available peers */
    @RequestMapping(value = "/peers/{peerId}", method = RequestMethod.DELETE)
    public void unregister(@PathVariable String peerId) {
        listPeers.remove(peerId);
    }

    /* Getting the metadata list and checking if it's empty */
    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public HashMap<String,Metadata> getMetadata()
    {
        if (mapperMetadata.isEmpty())
        {
            return null;
        }

        return mapperMetadata;
    }


    /* Deleting a specific peer from the list of available peer for a specific file
     *
       * In other words, a specific peer won't share a specific file anymore*/
    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable String fileId) {
        if(!mapperMetadata.containsKey(fileId))
            return ; // code d'erreur

        mapperMetadata.remove(fileId);
        FileDelete fileDelete = new FileDelete(fileId);
        if(!fileDelete.deleteFile())
            return ; // code d'erreur
    }


    /* Adding a new peer and file to the metadata list
     * If the file already exists in the metadata list, we only add the peer to the list of available peer for this list
      * returns an error if the peer already share the file*/
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public void uploadMetadata (@RequestBody Metadata fileMeta) {
        if(!mapperMetadata.containsKey(fileMeta.getFileId()))
            mapperMetadata.put(fileMeta.getFileId(), fileMeta);
    }

    /* Dynamic server URL depending on which file we want to ask to a specific peer
     * If this the server of this peer contains this file, he will be able to send it
       * if this peer is not in the list of the available peers for the file we ask, he wont be able to send it */
    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
    public String getFile(@PathVariable String fileId) {
        if(!mapperMetadata.containsKey(fileId))
            return null;

        FileReader fileData = new FileReader(fileId);

        return fileData.getData();
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.POST)
    public void uploadFile(@PathVariable String fileId, @RequestBody String content) {
        if(!mapperMetadata.containsKey(fileId))
            return ; // code d'erreur
        StringWriter fileWrite = new StringWriter(content,fileId);
        try {
            fileWrite.writeFile();
        }
        catch (IOException e) {
            return ; // code d'erreur
        }
    }

}