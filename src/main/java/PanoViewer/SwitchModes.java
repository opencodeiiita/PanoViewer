package PanoViewer;

import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static PanoViewer.Utils.imageutils.isRatio;

/**
 @author - Rohan Babbar
 Switching Modes between Flat and Panoramic Images
*/

public class SwitchModes extends JPanel implements PropertyChangeListener {

  CardLayout cardLayout;
  private FlatPanel flatPanel = FlatPanel.getInstance();
  private PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();

  private static SwitchModes instance;
  private BufferedImage cache;
  public static SwitchModes getInstance() {
    if (instance == null) {
      instance = new SwitchModes();
    }
    return instance;
  }

  private SwitchModes() {
    ModeRecorder.getInstance().addPropertyChangeListener(this);
    setBounds(50,50,400,400);
    setLayout(new CardLayout());
    FlatPanel flatPanel = FlatPanel.getInstance();
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    add(Mode.Flat.toString(),flatPanel);
    add(Mode.Panoramic.toString(),panoramicPanel);
    cardLayout = (CardLayout)getLayout();
    cardLayout.show(this,Mode.Panoramic.toString());
  }

  /**
   * Sets the image to be displayed.
   *
   * @param image the image to be set.
   */
  public void setImage(BufferedImage image) {
    cache=image;
    flatPanel.setImage(image);
    panoramicPanel.setImage(image);
    if(isRatio(image))
    {
      ModeRecorder.getInstance().setCurrentMode(Mode.Panoramic);
    }
    else {
      ModeRecorder.getInstance().setCurrentMode(Mode.Flat);

    }
  }

  public void switchingModes(Mode mode) {
    cardLayout.show(this,mode.toString());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("mode")) {
      Mode mode = (Mode) evt.getNewValue();
      switchingModes(mode);
    }
  }
}
