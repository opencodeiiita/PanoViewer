package PanoViewer.gui;

import java.awt.image.BufferedImage;

public interface ImageViewer {
    /*
     void setImage(Buffered Image)-
     returns- void

     */
      void setImage(BufferedImage image);
    /*
    void enableZoom()-Enables zoom feature
     */
    void enableZoom();
    /*
    void disableZoom- disables zoom feature
     */
     void disableZoom();
    /*
    Checks if the zoom is enabled or not.
    Returns true is zoom is enabled else false.
     */
     boolean isZoomEnabled();
    /*
    Checks if the Panning is enabled or not.
    Returns true is Panning is enabled else false.
     */
     boolean isPanningEnabled();
    /*
    Enables panning
     */
     void enablePanning();
    /*
    Disables panning
     */
     void disablePanning();


}
