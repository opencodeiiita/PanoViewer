package PanoViewer.gui;

import java.awt.image.BufferedImage;

/**
 * Image Viewer with zooming and panning for navigation.
 * @author kshan
 */
public interface ImageViewer extends Zoomable, Pannable {

  /**
   * Sets the Current Image
   *
   * @param image The image to set
   */
  void setImage(BufferedImage image);
}
