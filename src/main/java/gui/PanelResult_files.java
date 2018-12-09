package gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelResult_files extends JPanel {

    private JTable tableau;
    private List<String> list_files = new ArrayList<>();
    private JButton get_button = new JButton("Get");
    private JButton delete_button = new JButton("Delete");

    public PanelResult_files() {

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(new BorderLayout());
        // ----------------------------------

        // Panel north
        // ---------------------------------------------
        JLabel titre = new JLabel("Resulting Files");
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


        String title[] = {"FileId","Size", "FileName"};
        Object[][] data = {{"jhsdv","25","f1.txt"},{"ksdcn","25","f2.txt"}};

        tableau = new JTable(data,title);

        JScrollPane jsp = new JScrollPane(tableau);
        jsp.setPreferredSize(new Dimension(250,140));
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
        p_boutons.add(get_button);
        p_boutons.add(delete_button);
        p_actions.add(p_boutons, BorderLayout.CENTER);
        center.add(p_actions);
        this.add(center, BorderLayout.CENTER);
        // ---------------------------------------------

    }
}
