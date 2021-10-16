package PanoViewer;

import javax.swing.*;

/*
  @author:Rohan Babbar
  Look and Feel Class
 */

public class LookFeel {

  private UIManager.LookAndFeelInfo currentLook;
  private static LookFeel instance = new LookFeel();

  //Constructor for LookFeel
  private LookFeel() {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      currentLook = getAllLookAndFeel()[0];
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Get all the LookAndFeels installed in the system
  public UIManager.LookAndFeelInfo[] getAllLookAndFeel() {
    return UIManager.getInstalledLookAndFeels();
  }

  // GetCurrent LookAndFeel
  public UIManager.LookAndFeelInfo getCurrentLook() {
    return currentLook;
  }

  // Set Current LookAndFeel
  public void setCurrentLook(UIManager.LookAndFeelInfo currentLook) {
    this.currentLook = currentLook;
    try {
      UIManager.setLookAndFeel(currentLook.getClassName());
    }catch (Exception e) {
      e.printStackTrace();
    }
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        SwingUtilities.updateComponentTreeUI(MainScreen.getInstance());
      }
      });
    }

  public static LookFeel getInstance() {
    return instance;
  }
}
