package PanoViewer.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.net.URI;

/**
 *
 * @author kshan
 */
public class imageutils {

  private imageutils() {
    // private constructor to prevent instantiation
  }

  public static BufferedImage getFlipedImage(BufferedImage img) {
    int height = img.getHeight(null);
    int width = img.getWidth(null);
    WritableRaster raster
            = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 4,
                    null);
    ComponentColorModel colorModel = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[]{8, 8, 8, 8}, true, false, // bits, has Alpha, isAlphaPreMultiplied
            ComponentColorModel.TRANSLUCENT, // transparency
            DataBuffer.TYPE_BYTE);
    BufferedImage newImage = new BufferedImage(colorModel, raster, false, null);
    AffineTransform gt = new AffineTransform();
    gt.translate(0, height);
    gt.scale(1, -1d);
    Graphics2D g = newImage.createGraphics();
    g.transform(gt);
    g.drawImage(img, null, null);
    g.dispose();
    return newImage;
  }

  /**
   * isRatio method is to know whether the width to height ratio of image is 2:1 or not.
   *
   * @param img the image.
   * @return true when width to height ratio is 2:1 and false otherwise.
   */
  public static boolean isRatio(BufferedImage img) {
    // height of image
    int height = img.getHeight(null);
    // width of image
    int width = img.getWidth(null);

    boolean isTwoIsToOne = (width == 2 * height);
    if (isTwoIsToOne) {
      return true;
    }
    return false;
  }

  /*
  URL opener
   */
  public static void open(URI url) {
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();
      try {
        desktop.browse(url);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to launch the link, " + "your computer is likely misconfigured ." + "Cannot Open Link " + JOptionPane.WARNING_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null,
              "Java is not able to launch links on your computer.",
              "Cannot Launch Link", JOptionPane.WARNING_MESSAGE);

    }
  }

  public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
    int nw = icon.getIconWidth();
    int nh = icon.getIconHeight();
    if (icon.getIconWidth() > w) {
      nw = w;
      nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
    }
    if (nh > h) {
      nh = h;
      nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
    }

    return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
  }
}
