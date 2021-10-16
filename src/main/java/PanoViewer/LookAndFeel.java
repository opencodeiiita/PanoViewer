package PanoViewer;

import javax.swing.*;

public class LookAndFeel {

    private UIManager.LookAndFeelInfo currentLook;
    private static LookAndFeel instance = new LookAndFeel();

    private LookAndFeel() {

    }

    public UIManager.LookAndFeelInfo[] getAllLookAndFeel() {
        return UIManager.getInstalledLookAndFeels();
    }

    public UIManager.LookAndFeelInfo getCurrentLook() {
        return currentLook;
    }

    public void setCurrentLook(UIManager.LookAndFeelInfo currentLook) {
        this.currentLook = currentLook;
    }

    public static LookAndFeel getInstance() {
        return instance;
    }
}
