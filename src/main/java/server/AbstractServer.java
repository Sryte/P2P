package server;

import observer.Observable;
import observer.Observer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tools.Metadata;
import tools.Peer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractServer implements Observable {

    protected List<String> listPeers = new ArrayList<>();

    /* The metadata list is a hashmap to be able to attribute keys to files and peers */
    protected HashMap<String, Metadata> mapperMetadata = new HashMap<>();

    private ArrayList<Observer> listObserver = new ArrayList<>();

    public abstract void register(@RequestBody Peer peer);
    public abstract List<String> getListPeers();
    public abstract void unregister(@PathVariable String peerId);
    public abstract HashMap<String,Metadata> getMetadata();
    public abstract void deleteFile(@PathVariable String fileId);
    public abstract void uploadMetadata (@RequestBody Metadata fileMeta);
    public abstract String getFile(@PathVariable String fileId);
    public abstract void uploadFile(@PathVariable String fileId, @RequestBody String content);

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }

    public void notifyObserverListPeers(List<String> list) {
        for(Observer obs : listObserver)
            obs.updateListPeers(list);
    }
    public void notifyObserverMapperMetadata(HashMap<String, Metadata> mapper) {
        for(Observer obs : listObserver)
            obs.updateMapperMetadata(mapper);
    }

    public void setListPeers(List<String> listPeers) {
        this.listPeers = listPeers;
    }

    public void setMapperMetadata(HashMap<String, Metadata> mapperMetadata) {
        this.mapperMetadata = mapperMetadata;
    }
}
