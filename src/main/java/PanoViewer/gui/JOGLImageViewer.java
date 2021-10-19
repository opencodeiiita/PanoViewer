/*
 *
 */
package PanoViewer.gui;

import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

/**
 * JOGL based image viewer.
 * @author kshan
 */
public abstract class JOGLImageViewer extends GLJPanel implements GLEventListener, ImageViewer {
  public JOGLImageViewer() {
    addGLEventListener(this);
  }
}
