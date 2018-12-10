import controller.AbstractController;
import controller.Controller;
import gui.Client;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import server.AbstractServer;
import server.Server;

import java.awt.*;
import java.io.File;


public class Main {
    public static void main(String[] args){

        File share = new File("share");
        if(share.exists()) {
            String[] entries = share.list();
            File currentFile;
            for(String s : entries)
            {
                currentFile = new File(share.getPath(),s);
                currentFile.delete();
            }

        }
        else
            share.mkdir();

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Server.class).headless(false).run(args);

        EventQueue.invokeLater(() -> {
            AbstractServer serv = ctx.getBean(Server.class);
            AbstractController controller = new Controller(serv);
            Client client = new Client(controller);
            serv.addObserver(client);
        });


    }
}
