package PanoViewer;

import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
  @author - Rohan Babbar
  Switching Modes between Flat and Panoramic Images
 */

public class SwitchModes extends JFrame {

  JPanel mainPanel;
  CardLayout cardLayout;

  private static SwitchModes instance = new SwitchModes();
  private ImagePanel currentMode;

  public static SwitchModes getInstance() {
    return instance;
  }

  public ImagePanel getCurrentMode() {
    return currentMode;
  }

  public void setCurrentMode(ImagePanel currentMode) {
    this.currentMode = currentMode;
  }

  private SwitchModes() {
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    mainPanel = new JPanel(new CardLayout());
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    FlatPanel flatPanel = FlatPanel.getInstance();
    mainPanel.add(ImagePanel.FlatImages.toString(), flatPanel);
    mainPanel.add(ImagePanel.PanoramicImages.toString(), panoramicPanel);
    add(mainPanel);
    cardLayout = (CardLayout) mainPanel.getLayout();
    currentMode = ImagePanel.FlatImages;
    cardLayout.show(mainPanel, currentMode.toString());
  }

  public void switchingModes(ImagePanel imagePanel) {
    switch (imagePanel) {
      case FlatImages:
        currentMode = ImagePanel.FlatImages;
        cardLayout.show(mainPanel, currentMode.toString());
        break;
      case PanoramicImages:
        currentMode = ImagePanel.PanoramicImages;
        cardLayout.show(mainPanel, currentMode.toString());
        break;
    }
  }

  /*
   * Sets the image to be displayed.
   *
   * @param image the image to be set.
   */
  public static void setImage(BufferedImage image) {

  }
}
