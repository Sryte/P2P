package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PanelPeers extends JPanel {

    private List<String> list_peers = new ArrayList<>();
    private JTextField jtf = new JTextField();
    private JButton register_button = new JButton("Register");
    private JButton unregister_button = new JButton("Unregister");
    private JButton listPeers_button = new JButton("List Peers");
    private JButton listFiles_button = new JButton("List Files");

    public PanelPeers() {

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

        // test
        for(int i = 0 ; i<100 ; i++ )
            list_peers.add("192.168.10.4:8080");

        JList list = new JList(list_peers.toArray());
        JScrollPane jsp = new JScrollPane(list);
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
}
