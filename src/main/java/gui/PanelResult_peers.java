package gui;

import tools.Peer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelResult_peers extends JPanel {

    private List<Peer> listPeers = new ArrayList<>();
    private JButton register_button = new JButton("Register");

    private JList jList;
    JScrollPane jsp = new JScrollPane();

    public PanelResult_peers(Client.RegisterButtonListener registerButtonListener) {

        // Button Listening
        register_button.addActionListener(registerButtonListener);

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(new BorderLayout());
        // ----------------------------------

        // Panel north
        // ---------------------------------------------
        JLabel titre = new JLabel("Resulting Peers");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setOpaque(true);
        titre.setBackground(Color.lightGray);
        this.add(titre, BorderLayout.NORTH);
        // ---------------------------------------------

        // Panel center
        // ---------------------------------------------
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1));

        // Tableau des peers
        JPanel p_list = new JPanel();
        p_list.setLayout(new BorderLayout());
        JLabel l_tableau = new JLabel("List :");
        l_tableau.setBorder(new CompoundBorder(titre.getBorder(), new EmptyBorder(10,10,10,10)));
        l_tableau.setHorizontalAlignment(JLabel.CENTER);
        p_list.add(l_tableau, BorderLayout.NORTH);
        JPanel p_tableau = new JPanel();
        p_tableau.setLayout(new FlowLayout(FlowLayout.CENTER));

        jList = new JList(listPeers.toArray());
        jsp.add(jList);
        jsp.setPreferredSize(new Dimension(140,140));
        p_tableau.add(jsp);
        p_list.add(p_tableau, BorderLayout.CENTER);
        center.add(p_list);

        // Boutons des actions possibles
        JPanel p_actions = new JPanel();
        p_actions.setLayout(new BorderLayout());
        JLabel l_boutons = new JLabel("Actions :");
        l_boutons.setBorder(new CompoundBorder(titre.getBorder(), new EmptyBorder(10,10,10,10)));
        l_boutons.setHorizontalAlignment(JLabel.CENTER);
        p_actions.add(l_boutons, BorderLayout.NORTH);

        JPanel p_boutons = new JPanel();
        p_boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        p_boutons.add(register_button);
        p_actions.add(p_boutons, BorderLayout.CENTER);
        center.add(p_actions);
        this.add(center, BorderLayout.CENTER);
        // ---------------------------------------------
    }

    public void setListPeers(List<Peer> list_peers) {
        this.listPeers = list_peers;
    }

    public void refreshJSP() {
        jList = new JList(listPeers.toArray());
        jsp.setViewportView(jList);
    }

    public Peer getSelectedPeer() {
        int index = jList.getSelectedIndex();

        if(index==-1)
            return null;
        return listPeers.get(index);
    }
}
