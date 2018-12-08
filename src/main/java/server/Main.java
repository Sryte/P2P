package server;

import controller.AbstractController;
import controller.Controller;
import gui.Client;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args){

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Main.class).headless(false).run(args);

        EventQueue.invokeLater(() -> {
            AbstractServer serv = new Server();
            AbstractController controller = new Controller(serv);
            Client client = new Client(controller);
            serv.addObserver(client);
        });


    }
}
