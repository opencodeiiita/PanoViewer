package PanoViewer.gui;

import java.awt.image.BufferedImage;

public interface ImageViewer {
    /*
    Sets the current image
     void setImage(Buffered Image)-
     returns- void
     @param-image-The image to set

     */
      void setImage(BufferedImage image);
    /*
    @param-flag
    Enables zoom if flag is true else disables it.
    void enableZoom()-Enables zoom feature
     */
    void enableZoom(boolean flag);
    /**
     * Checks if zoom is enabled or not.
     *
     * @return {@code true} if the zooming is enabled,  {@code false} otherwise.
     */

    boolean isZoomEnabled();
    /**
     * Checks if panning is enabled or not.
     *
     * @return {@code true} if the zooming is enabled,  {@code false} otherwise.
     */

    boolean isPanningEnabled();
    /*
    @param-flag
    Enables panning is flag is true else disables panning
     */
     void enablePanning(boolean flag);

}
