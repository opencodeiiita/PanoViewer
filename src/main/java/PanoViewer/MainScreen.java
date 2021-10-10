package PanoViewer;

import PanoViewer.gui.Menu;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

  private JMenuBar menuBar = Menu.getInstance();
  public static JPanel jPanel;
  private static MainScreen instance = new MainScreen();

  public static MainScreen getInstance() {
    return instance;
  }

  private MainScreen() {
    setSize(600,600);
    setLayout(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setJMenuBar(menuBar);
    jPanel = new JPanel();
    jPanel.setBounds(50,50,400,400);
    jPanel.setBackground(Color.GRAY);
    add(jPanel);
  }

}

