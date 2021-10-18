package PanoViewer;

import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static PanoViewer.Utils.imageutils.isRatio;

/*
  @author - Rohan Babbar
  Switching Modes between Flat and Panoramic Images
 */

public class SwitchModes extends JPanel implements PropertyChangeListener {

  CardLayout cardLayout;

  private static SwitchModes instance = new SwitchModes();
  private static Mode currentMode;
  private BufferedImage cache;
  public static SwitchModes getInstance() {
    return instance;
  }

  public Mode getCurrentMode() {
    return currentMode;
  }

  public void setCurrentMode(Mode currentMode) {
    SwitchModes.currentMode = currentMode;
    switchingModes();
  }

  private SwitchModes() {
    addPropertyChangeListener(getInstance());
    setBounds(50,50,400,400);
    setLayout(new CardLayout());
    FlatPanel flatPanel = FlatPanel.getInstance();
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    add(Mode.Flat.toString(),flatPanel);
    add(Mode.Panoramic.toString(),panoramicPanel);
    cardLayout = (CardLayout)getLayout();
    setCurrentMode(Mode.Panoramic);
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
      setCurrentMode(Mode.Panoramic);
    }
    else {
      setCurrentMode(Mode.Flat);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    boolean flag= (boolean) propertyChangeEvent.getNewValue();
    if(!flag)
    {
      setCurrentMode(Mode.Flat);
    }
    else setCurrentMode(Mode.Panoramic);
  }
}
