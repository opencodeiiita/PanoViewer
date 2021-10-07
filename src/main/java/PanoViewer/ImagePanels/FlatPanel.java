package PanoViewer.ImagePanels;

import javax.swing.*;
import java.awt.*;

public class FlatPanel extends JPanel {

  private static FlatPanel instance = new FlatPanel();

  private FlatPanel() {
        setBackground(Color.BLUE);
    }

  public static FlatPanel getInstance() {
        return instance;
    }
}

