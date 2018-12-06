package gui;

import javax.swing.*;
import java.awt.*;

public class PanelResult extends JPanel {

    private PanelResult_infos p_infos = new PanelResult_infos();
    private PanelResult_peers p_peers = new PanelResult_peers();
    private PanelResult_files p_files = new PanelResult_files();

    public PanelResult() {

        // Configurations globales du panel
        // ----------------------------------
        this.setLayout(new CardLayout());
        //this.add(p_infos);
        //this.add(p_peers);
        this.add(p_files);

        // ----------------------------------

    }
}
