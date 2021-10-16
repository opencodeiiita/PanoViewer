/*
 *
 */
package PanoViewer.gui;

/**
 * Interface for panning.
 * @author kshan
 */
public interface Pannable {

  /**
   * Checks if panning is enabled or not.
   *
   * @return {@code true} if the zooming is enabled, {@code false} otherwise.
   */
  boolean isPanningEnabled();

  /**
   * Enables or disables Panning
   *
   * @param enable if true enables panning else disables it
   */
  void enablePanning(boolean enable);

  /**
   * Pans towards right if panX is positive otherwise towards left.
   * Pans towards up if panY is positive otherwise towards down.
   * @param panX
   * @param panY
   */
  void pan(float panX, float panY);

}
