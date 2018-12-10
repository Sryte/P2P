package gui;

import tools.Metadata;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PanelFiles extends JPanel {

    private HashMap<String, Metadata> mapperMetadata = new HashMap<>();

    private JTable tableau;
    JScrollPane jsp = new JScrollPane();
    private JButton share_button = new JButton("Share a new file");
    private JButton upload_button = new JButton("Upload");
    private JButton delete_button = new JButton("Delete");

    public PanelFiles(Client.ShareButtonListener shareButtonListener, Client.UploadButtonListenner uploadButtonListenner, Client.DeleteButtonListenner deleteButtonListenner) {

        // Button Listening
        share_button.addActionListener(shareButtonListener);
        upload_button.addActionListener(uploadButtonListenner);
        delete_button.addActionListener(deleteButtonListenner);

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

        String[][] data = new String[mapperMetadata.size()][3];
        int i = 0;
        for(String key : mapperMetadata.keySet()) {
            data[i][0] = mapperMetadata.get(key).getFileId();
            data[i][1] = String.valueOf(mapperMetadata.get(key).getSize());
            data[i][2] = mapperMetadata.get(key).getName();
            i++;
        }

        tableau = new JTable(data,title);

        jsp.add(tableau);
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

    public void refreshTableau() {
        String title[] = {"FileId","Size", "FileName"};

        String[][] data = new String[mapperMetadata.size()][3];
        int i = 0;
        for(String key : mapperMetadata.keySet()) {
            data[i][0] = mapperMetadata.get(key).getFileId();
            data[i][1] = String.valueOf(mapperMetadata.get(key).getSize());
            data[i][2] = mapperMetadata.get(key).getName();
            i++;
        }

        tableau = new JTable(data,title);
        jsp.setViewportView(tableau);
    }

    public void setMapperMetadata(HashMap<String, Metadata> mapperMetadata) {
        this.mapperMetadata = mapperMetadata;
    }

    public void addMetadata(Metadata metadata) {
        mapperMetadata.put(metadata.getFileId(), metadata);
    }

    public Metadata getSelectedFile() {
        int index = tableau.getSelectedRow();
        if(index==-1)
            return null;
        String key = tableau.getModel().getValueAt(index,0).toString();
        return mapperMetadata.get(key);
    }

    public void removeMetadata(String key) {
        mapperMetadata.remove(key);
    }
}