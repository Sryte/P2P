package gui;

import javax.swing.*;
import java.awt.*;

public class PanelResult extends JPanel {

    private CardLayout cardLayout = new CardLayout();

    public PanelResult(PanelResult_infos p_infos, PanelResult_peers p_peers, PanelResult_files p_files) {

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(cardLayout);
        this.add(p_infos,"INFOS");
        this.add(p_peers,"PEERS");
        this.add(p_files,"FILES");
        // ----------------------------------
    }

    public void changeCardLayout(String card) {
        cardLayout.show(this, card);
    }
}
