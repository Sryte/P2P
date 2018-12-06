package gui;

import tools.MyURL;

import javax.swing.*;
import java.awt.*;

public class PanelResult_infos extends JPanel {

    private MyURL url = new MyURL();
    private ImagePanel image = new ImagePanel("gui.png");

    public PanelResult_infos() {

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(new BorderLayout());
        // ----------------------------------

        // Panel north
        // ---------------------------------------------
        JLabel titre = new JLabel("Global Informations");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setOpaque(true);
        titre.setBackground(Color.lightGray);
        this.add(titre, BorderLayout.NORTH);
        // ---------------------------------------------

        // Panel center
        // ---------------------------------------------
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1));
        JLabel l_url = new JLabel("Your URL : "+url.getURL());
        l_url.setHorizontalAlignment(JLabel.CENTER);
        l_url.setVerticalAlignment(JLabel.CENTER);
        center.add(l_url);
        center.add(image);
        this.add(center, BorderLayout.CENTER);
        // ---------------------------------------------


    }
}
