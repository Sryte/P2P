package gui;


import tools.Peer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PanelPeers extends JPanel {

    private List<Peer> listPeers = new ArrayList<>();
    JScrollPane jsp = new JScrollPane();
    private JTextField jtf = new JTextField();
    private JList jList;
    private JButton register_button = new JButton("Register");
    private JButton unregister_button = new JButton("Unregister");
    private JButton listPeers_button = new JButton("List Peers");
    private JButton listFiles_button = new JButton("List Files");

    public PanelPeers(Client.RegisterButtonListener registerButtonListener, Client.UnregisterButtonListener unregisterButtonListener, Client.ListPeersButtonListener listPeersButtonListener, Client.ListFilesButtonListener listFilesButtonListener) {


        // Button Listening
        register_button.addActionListener(registerButtonListener);
        unregister_button.addActionListener(unregisterButtonListener);
        listPeers_button.addActionListener(listPeersButtonListener);
        listFiles_button.addActionListener(listFilesButtonListener);

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(new BorderLayout());
        // ----------------------------------


        // Panel north
        // ---------------------------------------------
        JLabel titre = new JLabel("List of Peers");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setOpaque(true);
        titre.setBackground(Color.lightGray);
        this.add(titre, BorderLayout.NORTH);
        // ---------------------------------------------

        // Panel center
        // ---------------------------------------------
        JPanel center = new JPanel();
        center.setLayout( new FlowLayout(FlowLayout.CENTER, 30, 10) );

        jList = new JList(listPeers.toArray());
        jsp.add(jList);
        jsp.setPreferredSize(new Dimension(140,140));
        center.add(jsp);

        // ajout des boutons
        center.add(unregister_button);
        center.add(listPeers_button);
        center.add(listFiles_button);

        this.add(center, BorderLayout.CENTER);
        // ---------------------------------------------

        // Panel south
        // ---------------------------------------------
        JPanel south = new JPanel();

        south.add(new JLabel("Add a new Peer : "));
        jtf.setPreferredSize(new Dimension(120,20));
        south.add(jtf);
        south.add(register_button);

        this.add(south, BorderLayout.SOUTH);
        // ---------------------------------------------

    }

    public void setListPeers(List<Peer> list_peers) {
        this.listPeers = list_peers;
    }

    public void addPeer(String peer) {
        listPeers.add(new Peer(peer));
    }

    public void refreshJSP() {
        /*List<String> s_list = new ArrayList<>();
        for(Peer p : listPeers)
            s_list.add(p.getUrl());*/
        jList = new JList(listPeers.toArray());
        jsp.setViewportView(jList);
    }

    public void setJtfText(String text) {
        jtf.setText(text);
    }

    public String getJtfText() {
        return jtf.getText();
    }

    public List<Peer> getListPeers() {
        return listPeers;
    }

    public Peer getSelectedPeer() {
        int index = jList.getSelectedIndex();

        if(index==-1)
            return null;
        return listPeers.get(index);
    }

    public void removePeer(Peer peer) {
        listPeers.remove(peer);
    }




}
