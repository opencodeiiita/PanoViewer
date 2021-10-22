package PanoViewer.ImagePanels;

import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import PanoViewer.gui.JOGLImageViewer;
import PanoViewer.Camera;

import java.awt.image.BufferedImage;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;

import java.awt.*;

import org.joml.Matrix4f;
import static PanoViewer.Utils.joglUtils.getTextureData;
import com.jogamp.common.nio.Buffers;
import java.nio.FloatBuffer;


/*
  @author - Rohan Babbar
  The Flat Image panel which will display Flat Images
*/

public class FlatPanel extends JOGLImageViewer {

  private boolean zoomEnable;
  private boolean panningEnable;
  private boolean updateImage;
  private float aspect;
  private Texture texture;
  private TextureData textureData;
  private int fov;
  private final Camera camera;
  private final Matrix4f pMat = new Matrix4f();
  private final Matrix4f vMat = new Matrix4f();
  private final Matrix4f mvMat = new Matrix4f();
  private final Matrix4f mMat = new Matrix4f();
  private static final int IDEAL_FOV = 90;
  private int mvLoc, projLoc;
  private final FloatBuffer vals = Buffers.newDirectFloatBuffer(16);
  // private HandleMouseEvent event;




  private static FlatPanel instance = new FlatPanel();

  private FlatPanel() {
    camera = new Camera();
    zoomEnable = true;
    panningEnable = true;

    // event = new HandleMouseEvent();
    // addMouseListener(event);
    // addMouseMotionListener(event);
    // addMouseWheelListener(event);
  }

  public static FlatPanel getInstance() {
      return instance;
  }

  public void display(GLAutoDrawable drawable){
    GL2 gl = drawable.getGL().getGL2();

    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

    if (updateImage) {
      texture.updateImage(gl, textureData);
    }

    mvMat.identity();
    mvMat.mul(vMat);
    mvMat.mul(mMat);

    gl.glUniformMatrix4fv(mvLoc, 1, false, mvMat.get(vals));
    gl.glUniformMatrix4fv(projLoc, 1, false, pMat.get(vals));
  }

  public void init(GLAutoDrawable drawable){
    GL2 gl = drawable.getGL().getGL2();

    gl.glClearColor(0, 0, 0, 1);
    pMat.setPerspective((float) Math.toRadians(fov), aspect, 0.1f, 1000.0f);
    vMat.set(camera.getViewMatrix());
  }

  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
    GL2 gl = drawable.getGL().getGL2();

    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();


  }

  public void dispose(GLAutoDrawable drawable){

  }

  public void enablePanning(boolean enable){

  }

  public boolean isPanningEnabled(){
    return panningEnable;
  }

  public void pan(float panX, float panY){

  }

  public void enableZoom(boolean enable){

  }

  public boolean isZoomEnabled(){
    return zoomEnable;
  }

  public void zoom(float zoomBy){

  }

  public void setImage(BufferedImage image){
    textureData = getTextureData(image);
    updateImage = true;
    repaint();
    camera.reset();
    fov = IDEAL_FOV;
  }
}
