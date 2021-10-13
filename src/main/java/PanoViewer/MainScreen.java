package PanoViewer;

import PanoViewer.gui.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;

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
    jPanel = new JPanel();
    jPanel.setBounds(50, 50, 400, 400);
    jPanel.setBackground(Color.GRAY);
    add(jPanel);
    jPanel.setDropTarget(new DropTarget() {
      public synchronized void drop(DropTargetDropEvent evt) {
        try {
          evt.acceptDrop(DnDConstants.ACTION_COPY);
          BufferedImage image = (BufferedImage) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
          SwitchModes.setImage(image);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      // image=SwitchModes.setImage(image);
    });

  }

  public static void main(String args[]) {
    MainScreen.getInstance();
  }
}
