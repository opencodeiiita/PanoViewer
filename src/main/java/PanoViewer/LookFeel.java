package PanoViewer;

import javax.swing.*;

/**
 *
 * @author Rohan Babbar
 * Look and Feel Class
 */

public class LookFeel {

  private UIManager.LookAndFeelInfo currentLook;
  private static LookFeel instance = new LookFeel();

  /**
   * Sets the look and feel to Metal and caches the first LaF returned by UIManager.
   */
  private LookFeel() {
      currentLook = getAllLookAndFeel()[0];
  }

  /**
   * Get All the look and feels
   * @return all the LookAndFeels
   */
  public UIManager.LookAndFeelInfo[] getAllLookAndFeel() {
    return UIManager.getInstalledLookAndFeels();
  }

  /**
   * Get Current LAF
   * @return the current LAF
   */
  public UIManager.LookAndFeelInfo getCurrentLook() {
    return currentLook;
  }

  /**
   * Set Current LAF to LAF returned by the UIManager
   * @param  currentLook The look and feel Info
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
