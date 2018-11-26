package arlo;

import java.util.ArrayList;
import java.util.HashMap;

/* For the metadata, we use hashmap to attribute a key
 * to each element of the list
  * There is message so the server is able to send the metadata hashmap and a message linked to it*/

public class ListMetadata {

    public HashMap<String,Metadata> mapperMetadata = new HashMap<String,Metadata>();

    private String message;

    public HashMap<String, Metadata> getMapperMetadata() {
        return mapperMetadata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMapperMetadata(HashMap<String, Metadata> mapperMetadata) {
        this.mapperMetadata = mapperMetadata;
    }

    public ListMetadata(HashMap<String, Metadata> mapperMetadata, String message) {
        this.mapperMetadata = mapperMetadata;
        this.message = message;
    }

}
