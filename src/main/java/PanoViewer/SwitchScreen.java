package PanoViewer;
import PanoViewer.ImagePanels.FlatPanel;
import PanoViewer.ImagePanels.PanoramicPanel;

import javax.swing.*;
import java.awt.*;

public class SwitchScreen extends JFrame {

  JPanel mainPanel;
  CardLayout cardLayout;

  private static SwitchScreen instance = new SwitchScreen();

  public static SwitchScreen getInstance() {
        return instance;
    }

  private SwitchScreen() {
    setSize(600,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    mainPanel = new JPanel(new CardLayout());
    PanoramicPanel panoramicPanel = PanoramicPanel.getInstance();
    FlatPanel flatPanel = FlatPanel.getInstance();
    mainPanel.add("FLAT",flatPanel);
    mainPanel.add("PANORAMIC",panoramicPanel);
    add(mainPanel);
    cardLayout = (CardLayout) mainPanel.getLayout();
    cardLayout.show(mainPanel,"FLAT");
  }

  public void switchingPanes(ImagePanel imagePanel) {
    switch (imagePanel) {
      case FlatImages:
        cardLayout.show(mainPanel,"FLAT");
        break;
      case PanoramicImages:
        cardLayout.show(mainPanel,"PANORAMIC");
        break;
    }
  }
}

