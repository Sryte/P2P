package client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception{
        ControllerClient ctrl = new ControllerClient();
        ctrl.getFilesData("192.168.43.252:8080","f1.txt");
        ctrl.uploadFilesMetadata("192.168.43.252:8080","f1.txt",64,"f1.txt");
        ctrl.getMetadata("192.168.43.252:8080");
        ctrl.uploadFilesData("192.168.43.252:8080","f1.txt","f1.txt");
        ctrl.registerPeers("192.168.43.252:8080","192.168.43.253:8080");
        ctrl.listPeers("192.168.43.252:8080");
        ctrl.unregisterPeers("192.168.43.252:8080","192.168.43.253:8080");
        ctrl.deleteFilesData("192.168.43.252:8080","f1.txt");
    }
}