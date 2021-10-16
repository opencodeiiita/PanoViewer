package PanoViewer;

import javax.swing.*;

/*
  @author:Rohan Babbar
  Look and Feel Class
*/

public class LookFeel {

  private LookAndFeel currentLook;
  private static LookFeel instance = new LookFeel();

  private LookFeel() {
    currentLook = UIManager.getLookAndFeel();
  }

  public UIManager.LookAndFeelInfo[] getAllLookAndFeel() {
    return UIManager.getInstalledLookAndFeels();
  }

  public LookAndFeel getCurrentLook() {
    return currentLook;
  }

  public void setCurrentLook(LookAndFeel currentLook) {
    this.currentLook = currentLook;
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }catch (Exception e) {
        e.printStackTrace();
      }
      JFrame.setDefaultLookAndFeelDecorated(true);
  }

  public static LookFeel getInstance() {
    return instance;
  }
}
