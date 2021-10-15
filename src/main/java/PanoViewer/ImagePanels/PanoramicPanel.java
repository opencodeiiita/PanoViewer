package PanoViewer.ImagePanels;

import javax.swing.*;
import java.awt.*;

/*
  @author - Rohan Babbar
  The Panoramic Image panel which will display Panoramic Images
*/

public class PanoramicPanel extends JPanel {

  private static PanoramicPanel instance = new PanoramicPanel();
  private JLabel label;

  private PanoramicPanel() {
    label = new JLabel();
    add(label);
  }

  public JLabel getLabel() {
        return label;
    }

  public static PanoramicPanel getInstance() {
        return instance;
    }
}
