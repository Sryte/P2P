package controller;

import server.AbstractServer;
import tools.Metadata;

import java.util.HashMap;
import java.util.List;

public class Controller extends AbstractController {

    public Controller(AbstractServer serv) {
        super(serv);
    }

    public void updatelistPeers(List<String> listPeers) {
        serv.setListPeers(listPeers);
    }

    public void updatemapperMetadata(HashMap<String, Metadata> mapperMetadata) {
        serv.setMapperMetadata(mapperMetadata);
    }
}
