package gui;


import controller.AbstractController;
import observer.Observer;
import server.Metadata;
import tools.MyURL;
import tools.RequestsClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;


public class Client extends JFrame implements Observer{

    private AbstractController controller;

    private RequestsClient rqt = new RequestsClient();
    private JPanel center_container = new JPanel();
    private PanelPeers panel_peers = new PanelPeers(new RegisterButtonListener(), new UnregisterButtonListener(), new ListPeersButtonListener(), new ListFilesButtonListener());
    private PanelFiles panel_files = new PanelFiles();
    private PanelResult panel_result = new PanelResult();
    private PanelLog panel_log = new PanelLog();
    private JPanel global_container = new JPanel();

    private final static int width = 900;
    private final static int height = 500;

    public Client(AbstractController controller){

        this.controller = controller;

        // Configurations générales de la fenêtre
        // -----------------------------------
        this.setTitle("Peer to Peer");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
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
        panel_peers.setListPeers(list);
    }
    public void updateMapperMetadata(HashMap<String, Metadata> mapper) {

    }

    class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            MyURL myURL = new MyURL();
            String url = panel_peers.getJtfText();
            panel_peers.setJtfText("");

            if(myURL.getURL().equals(url))
                panel_log.setTexte("You can't connect to yourself...");
            else if (panel_peers.getListPeers().contains(url))
                panel_log.setTexte("This peer is already registered !");
            else {
                try {
                    rqt.registerPeers(url,myURL.getURL());

                    panel_peers.addPeer(url);
                    controller.updatelistPeers(panel_peers.getListPeers());
                    panel_log.setTexte("Successful Register");

                } catch (Exception e) {
                    panel_log.setTexte("Error : Invalid URL ( Recall : IP:PORT )");
                }
            }

            panel_result.changeCardLayout("INFOS");
        }


    }

    class UnregisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            MyURL myURL = new MyURL();
            String url = panel_peers.getSelectedPeer();

            if(url.equals(""))
                panel_log.setTexte("You have to select a peer");
            else {
                try {
                    rqt.unregisterPeers(url,myURL.getURL());

                    panel_peers.removePeer(url);
                    controller.updatelistPeers(panel_peers.getListPeers());
                    panel_log.setTexte("Successful Unregister");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            panel_result.changeCardLayout("INFOS");
        }
    }


    class ListPeersButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            MyURL myURL = new MyURL();
            String url = panel_peers.getSelectedPeer();

            if(url.equals("")) {
                panel_log.setTexte("You have to select a peer");
                panel_result.changeCardLayout("INFOS");
            }
            else {
                try {
                    rqt.listPeers(url);

                    panel_result.changeCardLayout("PEERS");
                    panel_log.setTexte("Successful List Peers");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    class ListFilesButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            MyURL myURL = new MyURL();
            String url = panel_peers.getSelectedPeer();

            if(url.equals("")) {
                panel_log.setTexte("You have to select a peer");
                panel_result.changeCardLayout("INFOS");
            }
            else {
                try {
                    rqt.getMetadata(url);

                    panel_result.changeCardLayout("FILES");
                    panel_log.setTexte("Successful List Files");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}