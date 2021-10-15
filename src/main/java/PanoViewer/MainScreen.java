package PanoViewer;

import PanoViewer.gui.Menu;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.awt.dnd.DnDConstants.ACTION_COPY;

public class MainScreen extends JFrame implements DropTargetListener {

  private JMenuBar menuBar = Menu.getInstance();
  private JPanel jPanel;
  private static MainScreen instance = new MainScreen();

  public static MainScreen getInstance() {
    return instance;
  }

  private MainScreen() {
    setSize(600, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setJMenuBar(menuBar);
    jPanel = SwitchModes.getInstance();
    add(jPanel);
  }

  public static void main(String args[]) {
    MainScreen.getInstance();
  }

  @Override
  public void dragEnter(DropTargetDragEvent event) {

  }

  @Override
  public void dragOver(DropTargetDragEvent event) {

  }

  @Override
  public void dropActionChanged(DropTargetDragEvent event) {

  }

  @Override
  public void dragExit(DropTargetEvent event) {

  }

  @Override
  public void drop(DropTargetDropEvent event) {
    event.acceptDrop(ACTION_COPY);
    Transferable transferable = event.getTransferable();

    DataFlavor[] dataFlavors = transferable.getTransferDataFlavors();

    for (DataFlavor df : dataFlavors) {
      try {
        if (df.isFlavorJavaFileListType()) {
          List<File> files = (List<File>) transferable.getTransferData(df);
          File file = files.get(0);
          displayImage(file.getPath());
        }
      }catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void displayImage(String path) {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(path));
    }catch (IOException e) {
      e.printStackTrace();
    }
    SwitchModes.getInstance().setImage(image);
  }
}
