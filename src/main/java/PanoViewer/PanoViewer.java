/*
 * 
 */
package PanoViewer;

import PanoViewer.MainScreen;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author kshan
 */
public class PanoViewer {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    MainScreen mainScreen = new MainScreen();
    JFrame jFrame = new JFrame();
    jFrame.add(mainScreen);
    jFrame.setSize(1000, 1000);
    jFrame.setVisible(true);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // TODO code application logic here
  }
  
}
