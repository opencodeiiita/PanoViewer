package PanoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

  private static JFileChooser fileChooser;
  private static FileFilter fileFilter;

  /*
   * Returns a fileChooser with default filter for images.
   */
  public static JFileChooser getFileChooser() {
    if (fileChooser == null) {

      fileChooser = new JFileChooser();
    }
    if (fileFilter == null) {
      fileFilter = new FileNameExtensionFilter("Images Files", ImageIO.getReaderFileSuffixes());

    }
    fileChooser.setFileFilter(fileFilter);
    return fileChooser;
  }
}
