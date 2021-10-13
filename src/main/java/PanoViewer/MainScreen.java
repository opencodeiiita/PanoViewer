package PanoViewer;

import PanoViewer.gui.Menu;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

  private JMenuBar menuBar = Menu.getInstance();
  private JPanel jPanel;
  private static MainScreen instance = new MainScreen();

  public static MainScreen getInstance() {
    return instance;
  }

  private MainScreen() {
    setSize(600, 600);
    setLayout(null);
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
