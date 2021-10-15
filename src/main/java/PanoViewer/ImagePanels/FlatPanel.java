package PanoViewer.ImagePanels;

import javax.swing.*;

/*
  @author - Rohan Babbar
  The Flat Image panel which will display Flat Images
*/

public class FlatPanel extends JPanel {

  private static FlatPanel instance = new FlatPanel();

  private FlatPanel() {
  }

    public static FlatPanel getInstance() {
        return instance;
    }
}
