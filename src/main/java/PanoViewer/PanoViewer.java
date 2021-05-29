/*
 * 
 */
package PanoViewer;

import PanoViewer.Utils.IOUtils;
import PanoViewer.gui.PhotoSphere;
import java.awt.image.BufferedImage;
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
    BufferedImage img;
    try {
      img = ImageIO.read(IOUtils.getFileFromResource("test.jpg"));
    } catch (IOException ex) {
      Logger.getLogger(PanoViewer.class.getName()).log(Level.SEVERE, null, ex);
      return;
    }
    JFrame jFrame = new JFrame();
    jFrame.setSize(600,600);
    jFrame.add(new PhotoSphere(img, 200));
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setVisible(true);
  }

}
