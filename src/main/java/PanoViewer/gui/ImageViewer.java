package PanoViewer.gui;

import java.awt.image.BufferedImage;

public abstract class ImageViewer {
  public abstract void setImage(BufferedImage image);
  public abstract void enableZoom();
  public abstract void disableZoom();
  public abstract boolean isZoomEnabled();
  public abstract void enablePannig();
  public abstract void disablePannig();
  public abstract boolean isPanningEnabled();
}
