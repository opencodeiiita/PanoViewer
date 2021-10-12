package PanoViewer;

import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static PanoViewer.Utils.imageutils.isRatio;

/*
  @author - Rohan Babbar
  Switching Modes between Flat and Panoramic Images
 */

public class SwitchModes extends JFrame {

  JPanel mainPanel;
  CardLayout cardLayout;

  private static SwitchModes instance = new SwitchModes();
  private static ImagePanel currentMode;
  private static BufferedImage cache;
  public static SwitchModes getInstance() {
    return instance;
  }

  public ImagePanel getCurrentMode() {
    return currentMode;
  }

  public static void setCurrentMode(ImagePanel currentMode) {
    SwitchModes.currentMode = currentMode;
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

  private void switchingModes(ImagePanel imagePanel) {
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
    cache=image;
    if(isRatio(image))
    {
      setCurrentMode(currentMode.PanoramicImages);
    }
    else
      setCurrentMode(currentMode.FlatImages);

  }
}
