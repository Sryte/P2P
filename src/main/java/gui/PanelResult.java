package gui;

import javax.swing.*;
import java.awt.*;

public class PanelResult extends JPanel {

    private PanelResult_infos p_infos = new PanelResult_infos();
    private PanelResult_peers p_peers = new PanelResult_peers();
    private PanelResult_files p_files = new PanelResult_files();

    private CardLayout cardLayout = new CardLayout();

    public PanelResult() {

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
