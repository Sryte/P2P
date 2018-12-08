package observer;

import server.Metadata;

import java.util.HashMap;
import java.util.List;

public interface Observer {
    public void updateListPeers(List<String> list);
    public void updateMapperMetadata(HashMap<String, Metadata> mapper);
}
