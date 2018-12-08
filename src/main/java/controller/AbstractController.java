package controller;

import server.AbstractServer;
import server.Metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractController {

    protected AbstractServer serv;
    protected List<String> listPeers = new ArrayList<>();
    protected HashMap<String, Metadata> mapperMetadata = new HashMap<>();

    public AbstractController(AbstractServer serv) {
        this.serv = serv;
    }

    public abstract void updatelistPeers(List<String> listPeers);

    public abstract void updatemapperMetadata(HashMap<String, Metadata> mapperMetadata);

}
