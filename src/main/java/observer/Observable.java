package observer;

import tools.Metadata;

import java.util.HashMap;
import java.util.List;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserverListPeers(List<String> list);
    public void notifyObserverMapperMetadata(HashMap<String, Metadata> mapper);
}
