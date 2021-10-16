/*
 *
 */
package PanoViewer.gui;

/**
 *
 * @author kshan
 */
public interface Zoomable {

  /**
   * Enables or disables zoom
   *
   * @param enable if true enables zoom else disables it
   */
  void enableZoom(boolean enable);

  /**
   * Checks if zoom is enabled or not.
   *
   * @return {@code true} if the zooming is enabled, {@code false} otherwise.
   */
  boolean isZoomEnabled();

  /**
   * Zooms inwards if zoomBy is positive otherwise outwards.
   * @param zoomBy The amount to zoom by.
   */
  void zoom(float zoomBy);
}
