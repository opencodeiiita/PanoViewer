package PanoViewer.ImagePanels;

import javax.swing.*;
import java.awt.*;

public class PanoramicPanel extends JPanel {

  private static PanoramicPanel instance = new PanoramicPanel();

  private PanoramicPanel() {
        setBackground(Color.BLACK);
    }

  public static PanoramicPanel getInstance() {
        return instance;
    }
}

