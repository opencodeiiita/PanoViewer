package PanoViewer;

import javax.swing.*;

/*
  @author:Rohan Babbar
  Look and Feel Class
 */

public class LookFeel {

  private UIManager.LookAndFeelInfo currentLook;
  private static LookFeel instance = new LookFeel();

  /*
    Sets the look and feel to Metal and caches the first LaF returned by UIManager.
   */
  private LookFeel() {
      currentLook = getAllLookAndFeel()[0];
  }

  /*
    Get all the LookAndFeels installed in the system
   */
  public UIManager.LookAndFeelInfo[] getAllLookAndFeel() {
    return UIManager.getInstalledLookAndFeels();
  }

  /*
    GetCurrent Look and Feel
   */
  public UIManager.LookAndFeelInfo getCurrentLook() {
    return currentLook;
  }

  /*
    Set Current Look and Feel to the LAF returned by UIManager
   */
  public void setCurrentLook(UIManager.LookAndFeelInfo currentLook) {
    try {
      UIManager.setLookAndFeel(currentLook.getClassName());
      this.currentLook = currentLook;
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
