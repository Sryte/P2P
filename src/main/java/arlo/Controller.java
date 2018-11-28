package arlo;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    public final ArrayList<String> listPeers = new ArrayList<String>();

    /* The metadata list is a hashmap to be able to attribute keys to files and peers */
    public final HashMap<String,Metadata> mapperMetadata = new HashMap<String,Metadata>();

    public final MyURL myURL = new MyURL();

    /* adding a specific peer to the list of peers of the network */
    @RequestMapping(value = "/peers", method = RequestMethod.POST)
    public Message messageRegister(@RequestBody Peer peer) {
        if(!listPeers.contains(peer.getURL()) && listPeers.add(peer.getURL()))
            return new Message("SUCCESS : You're now connected to the P2P network");

        return new Message("FAIL : You were unable to connect to the network");
    }

    /* Obtain the list of available peers in the network and checking if the list is empty*/
    @RequestMapping(value = "/peers", method = RequestMethod.GET)
    public ListPeer pullList()
    {
        if (listPeers.isEmpty())
        {
            return new ListPeer(null,"WARNING : the list of peers is empty");
        }

        return new ListPeer(listPeers,"SUCCESS : The list isn't empty and contain the following peers");
    }

    /* Delete a specific peer in the list of the available peers */
    @RequestMapping(value = "/peers/{peerId}", method = RequestMethod.DELETE)
    public Message messageUnregister(@PathVariable String peerId) {
        if(listPeers.remove(peerId))
        {
            return new Message("SUCCESS : You're now disconnected to the P2P network");
        }

        return new Message("FAIL : This Peer is not in list of peers");
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

    /*
    //Deleting a specific peer from the list of available peer for a specific file
      *
       * In other words, a specific peer won't share a specific file anymore
    @RequestMapping(value = "/files", method = RequestMethod.DELETE)
    public Message messageDeleteMetadata(@RequestBody FileMetadata file) {
        if(mapperMetadata.containsKey(file.getFileId()) && mapperMetadata.get(file.getFileId()).getListPeers().remove(file.getPeerId()))
        {
            return new Message("SUCCESS : This peer doesn't share this file anymore");
        }

        return new Message("FAIL : No file or peer corresponding in the metadata list");
    }
    */

    /* Adding a new peer and file to the metadata list
     * If the file already exists in the metadata list, we only add the peer to the list of available peer for this list
      * returns an error if the peer already share the file*/
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public void uploadMetadata (@RequestBody Metadata fileMeta) {
        if(!mapperMetadata.containsKey(fileMeta.getFileId()))
            mapperMetadata.put(fileMeta.getFileId(), fileMeta);
    }

    /*
    //Dynamic server URL depending on which file we want to ask to a specific peer
     * If this the server of this peer contains this file, he will be able to send it
       * if this peer is not in the list of the available peers for the file we ask, he wont be able to send it
    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
    public FileData messageGetFile(@PathVariable String fileId) {
        if(!mapperMetadata.containsKey(fileId))
            return new FileData("FAIL : FileMetadata doesn't exist in the metadata list","");
        else if(!mapperMetadata.get(fileId).getListPeers().contains(myURL.getURL()))
        {
            return new FileData("FAIL : This peer doesn't share the file "+fileId,"");
        }

        FileReader fileData = new FileReader(fileId);

        return new FileData("SUCCESS : FileMetadata sent", fileData.getData());
    }

    */

}
