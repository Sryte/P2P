package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelFiles extends JPanel {

    private JTable tableau;
    private List<String> list_files = new ArrayList<>();
    private JTextField jtf = new JTextField();
    private JButton share_button = new JButton("Share a new file");
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

        String title[] = {"FileId","Size", "FileName"};
        Object[][] data = {{"jhjdvuhuhxvuhisjcbisvbudsdv","25","f1.txt"},{"ksdcn","25","f2.txt"}};

        tableau = new JTable(data,title);

        JScrollPane jsp = new JScrollPane(tableau);
        jsp.setPreferredSize(new Dimension(250,140));
        center.add(jsp);

        // ajout des boutons
        center.add(upload_button);
        center.add(delete_button);

        this.add(center, BorderLayout.CENTER);
        // ---------------------------------------------

        // Panel south
        // ---------------------------------------------
        JPanel south = new JPanel();

        south.add(share_button);

        this.add(south, BorderLayout.SOUTH);
        // ---------------------------------------------

    }
}