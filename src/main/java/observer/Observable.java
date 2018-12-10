package observer;

import tools.Metadata;
import tools.Peer;

import java.util.HashMap;
import java.util.List;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserverListPeers(List<Peer> list);
    public void notifyObserverMapperMetadata(HashMap<String, Metadata> mapper);
}
