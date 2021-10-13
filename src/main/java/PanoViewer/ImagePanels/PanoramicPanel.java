package PanoViewer.ImagePanels;

import PanoViewer.gui.JOGLImageViewer;
import PanoViewer.gui.Pannable;
import PanoViewer.gui.Zoomable;
import com.jogamp.opengl.GLAutoDrawable;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rohan Babbar
 * Panaromic Panel which displays Panoramic Images
*/

public class PanoramicPanel extends JOGLImageViewer {

  private static PanoramicPanel instance = new PanoramicPanel();

  private PanoramicPanel() {

  }

  public static PanoramicPanel getInstance() {
        return instance;
    }

  @Override
  public void setImage(BufferedImage image) {

  }

  @Override
  public boolean isPanningEnabled() {
    return false;
  }

  @Override
  public void enablePanning(boolean enable) {

  }

  @Override
  public void pan(float panX, float panY) {

  }

  @Override
  public void enableZoom(boolean enable) {

  }

  @Override
  public boolean isZoomEnabled() {
    return false;
  }

  @Override
  public void zoom(float zoomBy) {

  }

  @Override
  public void init(GLAutoDrawable drawable) {

  }

  @Override
  public void dispose(GLAutoDrawable drawable) {

  }

  @Override
  public void display(GLAutoDrawable drawable) {

  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

  }

/**
   *
   * Handles Mouse Events for Panning and Zooming
*/

  private static class HandleMouseEvent extends MouseAdapter implements Zoomable,Pannable {
    @Override
    public boolean isPanningEnabled() {
      return false;
    }

    @Override
    public void enablePanning(boolean enable) {

    }

    @Override
    public void pan(float panX, float panY) {

    }

    @Override
    public void enableZoom(boolean enable) {

    }

    @Override
    public boolean isZoomEnabled() {
      return false;
    }

    @Override
    public void zoom(float zoomBy) {

    }

  }
}
