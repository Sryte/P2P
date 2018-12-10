package controller;

import server.AbstractServer;
import tools.Metadata;
import tools.Peer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractController {

    protected AbstractServer serv;

    public AbstractController(AbstractServer serv) {
        this.serv = serv;
    }

    public abstract void updatelistPeers(List<Peer> listPeers);

    public abstract void updatemapperMetadata(HashMap<String, Metadata> mapperMetadata);

}
