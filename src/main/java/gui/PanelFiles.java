package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelFiles extends JPanel {

    private List<String> list_files = new ArrayList<>();
    private JTextField jtf = new JTextField();
    private JButton share_button = new JButton("Share");
    private JButton upload_button = new JButton("Upload");
    private JButton delete_button = new JButton("Delete");

    public PanelFiles() {

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(new BorderLayout());
        // ----------------------------------

        // Panel north
        // ---------------------------------------------
        JLabel titre = new JLabel("List of Files");
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
            list_files.add("f1.txt");

        JList list = new JList(list_files.toArray());

        JScrollPane jsp = new JScrollPane(list);
        jsp.setPreferredSize(new Dimension(140,140));
        center.add(jsp);

        // ajout des boutons
        center.add(upload_button);
        center.add(delete_button);

        this.add(center, BorderLayout.CENTER);
        // ---------------------------------------------

        // Panel south
        // ---------------------------------------------
        JPanel south = new JPanel();

        south.add(new JLabel("Share a new File : "));
        jtf.setPreferredSize(new Dimension(120,20));
        south.add(jtf);
        south.add(share_button);

        this.add(south, BorderLayout.SOUTH);
        // ---------------------------------------------

    }
}