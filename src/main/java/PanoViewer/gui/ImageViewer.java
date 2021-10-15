package PanoViewer.gui;

import java.awt.image.BufferedImage;

public interface ImageViewer {
    /**
     * Sets the Current Image
     * @param image The image to set
     */

    void setImage(BufferedImage image);
    /**
     * Enables or disables zoom
     *
     * @param flag if true enables zoom else disables it
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
    /**
     * Enables or disables Panning
     *
     * @param flag if true enables panning else disables it
     */

    void enablePanning(boolean flag);

}
