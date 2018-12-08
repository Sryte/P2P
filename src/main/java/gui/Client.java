package gui;


import controller.AbstractController;
import observer.Observer;
import server.Metadata;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class Client extends JFrame implements Observer{

    protected JPanel center_container = new JPanel();
    protected PanelPeers panel_peers;
    protected PanelFiles panel_files = new PanelFiles();
    protected JPanel panel_result = new PanelResult();
    protected JPanel panel_log = new PanelLog();
    protected JPanel global_container = new JPanel();

    private final static int width = 900;
    private final static int height = 500;

    public Client(AbstractController controller){

        // Configurations générales de la fenêtre
        // -----------------------------------
        this.setTitle("Peer to Peer");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // -----------------------------------

        // Creation des panels
        // -----------------------------------
        panel_peers = new PanelPeers(controller);
        // -----------------------------------

        initComposant();

        // On rend la fenêtre visible
        this.setVisible(true);
    }

    private void initComposant(){
        /*
        // Backgrounds de test
        panel_peers.setBackground(Color.cyan);
        panel_result.setBackground(Color.green);
        panel_log.setBackground(Color.gray);*/


        // Dimensionnement des panels
        // -----------------------------------------------
        panel_peers.setPreferredSize(new Dimension(600,225));
        panel_files.setPreferredSize(new Dimension(600,225));
        center_container.setPreferredSize(new Dimension(600,450));
        panel_result.setPreferredSize(new Dimension(300,450));
        panel_log.setPreferredSize(new Dimension(900,50));
        // -----------------------------------------------

        // Bordure
        center_container.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));


        // Imbrication des panels
        // -----------------------------------------------
        center_container.setLayout(new GridLayout(2, 1));
        center_container.add(panel_peers);
        center_container.add(panel_files);

        global_container.setLayout(new BorderLayout());
        global_container.add(center_container, BorderLayout.CENTER);
        global_container.add(panel_result, BorderLayout.EAST);
        global_container.add(panel_log, BorderLayout.SOUTH);
        // -----------------------------------------------

        // Ajout du conteneur global à la fenêtre
        this.setContentPane(global_container);
    }

    public void updateListPeers(List<String> list) {
        panel_peers.setlistPeers(list);
    }
    public void updateMapperMetadata(HashMap<String, Metadata> mapper) {

    }


}