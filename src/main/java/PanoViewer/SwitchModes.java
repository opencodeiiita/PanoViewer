package PanoViewer;
import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;

import javax.swing.*;
import java.awt.*;

/*
  @author - Rohan Babbar
  Switching Modes between Flat and Panoramic Images
 */

public class SwitchModes extends JFrame {

  JPanel mainPanel;
  CardLayout cardLayout;

  private static SwitchModes instance = new SwitchModes();
  private static final String FLAT_IMAGES = "FLAT";
  private static final String PANORAMIC_IMAGES = "PANORAMIC";
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
    setSize(600,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    mainPanel = new JPanel(new CardLayout());
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    FlatPanel flatPanel = FlatPanel.getInstance();
    mainPanel.add(FLAT_IMAGES,flatPanel);
    mainPanel.add(PANORAMIC_IMAGES,panoramicPanel);
    add(mainPanel);
    cardLayout = (CardLayout) mainPanel.getLayout();
    currentMode = ImagePanel.FlatImages;
    cardLayout.show(mainPanel,FLAT_IMAGES);
  }

  public void switchingModes(ImagePanel imagePanel) {
    switch (imagePanel) {
      case FlatImages:
        currentMode = ImagePanel.FlatImages;
        cardLayout.show(mainPanel,FLAT_IMAGES);
        break;
    case PanoramicImages:
        currentMode = ImagePanel.PanoramicImages;
        cardLayout.show(mainPanel,PANORAMIC_IMAGES);
        break;
    }
  }
}

