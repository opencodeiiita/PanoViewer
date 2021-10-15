package PanoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static java.awt.dnd.DnDConstants.ACTION_COPY;

/*
    @author - Rohan Babbar
    Used to drag and drop files
 */

public class DragListener implements DropTargetListener {

  private JLabel label;
  private BufferedImage image;

  public DragListener(JLabel label) {
    this.label = label;
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
              for (File file : files) {
                displayImage(file.getPath());
                }
              }
        }catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    private void displayImage(String path) {
      image = null;
      try {
        image = ImageIO.read(new File(path));
      }catch (IOException e) {
        e.printStackTrace();
      }
      ImageIcon icon = new ImageIcon(image);
      label.setIcon(icon);
    }

    public BufferedImage getImage() {
      return image;
    }
}
