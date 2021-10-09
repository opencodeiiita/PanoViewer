/*
 *
 */
package PanoViewer;

import PanoViewer.Utils.IOUtils;
import PanoViewer.gui.PhotoSphere;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    if (args.length < 1) {
      return;
    }
    BufferedImage img;
    try {
      img = ImageIO.read(new File(args[0]));
    } catch (IOException ex) {
      Logger.getLogger(PanoViewer.class.getName()).log(Level.SEVERE, null, ex);
      return;
    }
    JFrame jFrame = new JFrame("PanoViewer");
    jFrame.setSize(600, 600);
    PhotoSphere ps = new PhotoSphere();
    jFrame.add(ps);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setVisible(true);
    ps.replaceImage(img);
  }
}
