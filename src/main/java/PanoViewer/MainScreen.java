package PanoViewer;

import PanoViewer.ImagePanels.PanoramicPanel;
import PanoViewer.gui.Menu;
import javax.swing.*;
import java.awt.dnd.DropTarget;
import java.awt.image.BufferedImage;

public class MainScreen extends JFrame {

  private JLabel imageLabel;
  private JMenuBar menuBar = Menu.getInstance();
  private JPanel jPanel;
  private static MainScreen instance = new MainScreen();

  public void dragAndDrop() {
    imageLabel = PanoramicPanel.getInstance().getLabel();
    DragListener listener = new DragListener(imageLabel);
    new DropTarget(PanoramicPanel.getInstance(),listener);
    BufferedImage image = listener.getImage();
    if (image!=null) {
      SwitchModes.getInstance().setImage(image);
    }
  }

  public static MainScreen getInstance() {
    return instance;
  }

  private MainScreen() {
    setSize(600, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setJMenuBar(menuBar);
    jPanel = SwitchModes.getInstance();
    add(jPanel);
  }

  public static void main(String args[]) {
    MainScreen.getInstance();
  }
}
