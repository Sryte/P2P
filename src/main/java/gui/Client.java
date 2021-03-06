package gui;


import controller.AbstractController;
import observer.Observer;
import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.List;


public class Client extends JFrame implements Observer{

    private AbstractController controller;
    final JFileChooser fc = new JFileChooser();

    private RequestsClient rqt = new RequestsClient();
    private JPanel center_container = new JPanel();
    private PanelPeers panel_peers = new PanelPeers(new RegisterButtonListener(), new UnregisterButtonListener(), new ListPeersButtonListener(), new ListFilesButtonListener());
    private PanelFiles panel_files = new PanelFiles(new ShareButtonListener(), new UploadButtonListenner(), new DeleteButtonListenner());
    private PanelResult_infos panelResultInfos = new PanelResult_infos();
    private PanelResult_peers panelResultPeers = new PanelResult_peers(new RegisterButtonListener());
    private PanelResult_files panelResultFiles = new PanelResult_files();
    private PanelResult panel_result;
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

        // Init panel result
        panel_result = new PanelResult(panelResultInfos,panelResultPeers,panelResultFiles);


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

    public void updateListPeers(List<Peer> list) {
        panel_peers.setListPeers(list);
        panel_peers.refreshJSP();
    }
    public void updateMapperMetadata(HashMap<String, Metadata> mapper) {
        panel_files.setMapperMetadata(mapper);
        panel_files.refreshTableau();
    }

    class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            MyURL myURL = new MyURL();
            String url = new String();
            if(event.getSource() == panel_peers.getRegister_button())
                url = panel_peers.getJtfText();
            else {
                Peer p = panelResultPeers.getSelectedPeer();
                url = p.getUrl();
            }
            panel_peers.setJtfText("");

            if(myURL.getURL().equals(url))
                panel_log.setTexte("You can't connect to yourself...");
            else if (panel_peers.getListPeers().contains(url))
                panel_log.setTexte("This peer is already registered !");
            else {
                try {
                    rqt.registerPeers(url,myURL.getURL());

                    panel_peers.addPeer(url);
                    panel_peers.refreshJSP();
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
            Peer peer = panel_peers.getSelectedPeer();

            if(peer == null)
                panel_log.setTexte("You have to select a peer");
            else {
                try {
                    rqt.unregisterPeers(peer.getUrl(),myURL.getURL());

                    panel_peers.removePeer(peer);
                    panel_peers.refreshJSP();
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
            Peer peer = panel_peers.getSelectedPeer();

            if(peer == null) {
                panel_log.setTexte("You have to select a peer");
                panel_result.changeCardLayout("INFOS");
            }
            else {
                try {
                    List<Peer> list = rqt.getListPeers(peer.getUrl());

                    panelResultPeers.setListPeers(list);
                    panelResultPeers.refreshJSP();

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
            Peer peer = panel_peers.getSelectedPeer();

            if(peer == null) {
                panel_log.setTexte("You have to select a peer");
                panel_result.changeCardLayout("INFOS");
            }
            else {
                try {
                    HashMap<String, Metadata> mapperMetadata = rqt.getMetadata(peer.getUrl());

                    panel_files.setMapperMetadata(mapperMetadata);
                    panel_files.refreshTableau();

                    panel_result.changeCardLayout("FILES");
                    panel_log.setTexte("Successful List Files");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ShareButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int returnVal = fc.showOpenDialog(Client.this);

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String name = file.getName();
                long size = file.length();
                long hash = name.hashCode() * String.valueOf(size).hashCode();

                if(hash<0)
                    hash*=-1;

                String fileId = String.valueOf(hash);

                File dest = new File("share/"+fileId);

                CopyFile cp = new CopyFile();
                try {
                    cp.copyFileUsingStream(file, dest);
                    panel_files.addMetadata(new Metadata(fileId,size,name));
                    panel_files.refreshTableau();
                    controller.updatemapperMetadata(panel_files.getMapperMetadata());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class UploadButtonListenner implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Metadata metadata = panel_files.getSelectedFile();
            List<Peer> listPeer = panel_peers.getListPeers();

            if(metadata == null)
                panel_log.setTexte("You have to select a file");
            else if(listPeer == null)
                panel_log.setTexte("There is no peer connected");
            else {
                try {
                    for(Peer p : listPeer) {
                        rqt.uploadFilesMetadata(p.getUrl(),metadata);
                        rqt.uploadFilesData(p.getUrl(),metadata.getFileId());
                    }

                    panel_log.setTexte("Successful Upload");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            panel_result.changeCardLayout("INFOS");
        }
    }

    class DeleteButtonListenner implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Metadata metadata = panel_files.getSelectedFile();
            if(metadata == null)
                panel_log.setTexte("You have to select a file");
            else {
                FileDelete fd = new FileDelete(metadata.getFileId());
                if(fd.deleteFile())
                    panel_log.setTexte("File correctly suppressed");
                else
                    panel_log.setTexte("File is unfortunately not suppressed...");
                panel_files.removeMetadata(metadata.getFileId());
                panel_files.refreshTableau();
                controller.updatemapperMetadata(panel_files.getMapperMetadata());
            }
        }
    }
}