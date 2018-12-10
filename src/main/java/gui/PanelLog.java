package gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelLog extends JPanel {

    JLabel texte = new JLabel("");

    public PanelLog() {

        // Configurations globales du panel
        // ----------------------------------
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.gray));
        this.setLayout(new BorderLayout());
        // ----------------------------------

        // Panel west
        // ----------------------------------
        JLabel titre = new JLabel("Logs : ");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setVerticalAlignment(JLabel.CENTER);
        titre.setBorder(new CompoundBorder(titre.getBorder(), new EmptyBorder(10,10,10,10)));
        this.add(titre, BorderLayout.WEST);
        // ----------------------------------

        // Panel center
        // ----------------------------------
        texte.setHorizontalAlignment(JLabel.CENTER);
        texte.setVerticalAlignment(JLabel.CENTER);
        this.add(texte, BorderLayout.CENTER);
        // ----------------------------------
    }

    public void setTexte(String texte) {
        this.texte.setText(texte);
    }
}
