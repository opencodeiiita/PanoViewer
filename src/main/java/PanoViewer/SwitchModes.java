package PanoViewer;

import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;
import PanoViewer.gui.Menu;

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
    switchingModes();
  }

  private SwitchModes() {
    setBounds(50,50,400,400);
    setLayout(new CardLayout());
    FlatPanel flatPanel = FlatPanel.getInstance();
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    add(ImagePanel.FlatImages.toString(),flatPanel);
    add(ImagePanel.PanoramicImages.toString(),panoramicPanel);
    cardLayout = (CardLayout)getLayout();
    setCurrentMode(ImagePanel.PanoramicImages);
    cardLayout.show(this,currentMode.toString());
  }

  private void switchingModes() {
    cardLayout.show(this,currentMode.toString());
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
      setCurrentMode(ImagePanel.PanoramicImages);
    }
    else {
      setCurrentMode(ImagePanel.FlatImages);
    }
  }
}
