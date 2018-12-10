package observer;

import tools.Metadata;
import tools.Peer;

import java.util.HashMap;
import java.util.List;

public interface Observer {
    public void updateListPeers(List<Peer> list);
    public void updateMapperMetadata(HashMap<String, Metadata> mapper);
}
