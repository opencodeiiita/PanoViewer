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

public class SwitchModes extends JPanel {

  CardLayout cardLayout;

  private static SwitchModes instance = new SwitchModes();
  private static ImagePanel currentMode;
  private BufferedImage cache;
  public static SwitchModes getInstance() {
    return instance;
  }

  public ImagePanel getCurrentMode() {
    return currentMode;
  }

  public void setCurrentMode(ImagePanel currentMode) {
    SwitchModes.currentMode = currentMode;
  }

  private SwitchModes() {
    setBounds(50,50,400,400);
    setLayout(new CardLayout());
    FlatPanel flatPanel = FlatPanel.getInstance();
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    add(ImagePanel.FlatImages.toString(),flatPanel);
    add(ImagePanel.PanoramicImages.toString(),panoramicPanel);
    cardLayout = (CardLayout)getLayout();
    currentMode = ImagePanel.PanoramicImages;
    cardLayout.show(this,currentMode.toString());
  }

  public void switchingModes(ImagePanel imagePanel) {
    switch (imagePanel) {
      case FlatImages:
        currentMode = ImagePanel.FlatImages;
        cardLayout.show(SwitchModes.getInstance(),currentMode.toString());
        break;
      case PanoramicImages:
        currentMode = ImagePanel.PanoramicImages;
        cardLayout.show(SwitchModes.getInstance(),currentMode.toString());
        break;
    }
  }

  /*
   * Sets the image to be displayed.
   *
   * @param image the image to be set.
   */
  public void setImage(BufferedImage image) {
    cache=image;
    if(isRatio(image))
    {
      setCurrentMode(currentMode.PanoramicImages);
    }
    else
      setCurrentMode(currentMode.FlatImages);

  }
}
