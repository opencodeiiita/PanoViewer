package PanoViewer.ImagePanels;

import PanoViewer.gui.JOGLImageViewer;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import static PanoViewer.Utils.joglUtils.getTextureData;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import java.awt.image.BufferedImage;

/**
 @author - Rohan Babbar
 The Flat Image panel which will display Flat Images
 */

public class FlatPanel extends JOGLImageViewer {

  private boolean updateImage;
  private TextureData textureData;
  private Texture texture;
  private static FlatPanel instance = new FlatPanel();

  private FlatPanel() {

  }

  @Override
  public void setImage(BufferedImage image) {
    updateImage = true;
    textureData = getTextureData(image);
    repaint();
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
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    texture = new Texture(GL_TEXTURE_2D);
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    texture.destroy(gl);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    if (updateImage) {
      texture.updateImage(gl,textureData);
    }
    texture.enable(gl);
    texture.bind(gl);
    TextureCoords coordinates = texture.getImageTexCoords();
    gl.glBegin(GL2.GL_QUADS);
    gl.glTexCoord2f(coordinates.left(), coordinates.bottom());
    gl.glVertex3f(0, 0, 0);
    gl.glTexCoord2f(coordinates.right(), coordinates.bottom());
    gl.glVertex3f(1, 0, 0);
    gl.glTexCoord2f(coordinates.right(), coordinates.top());
    gl.glVertex3f(1, 1, 0);
    gl.glTexCoord2f(coordinates.left(), coordinates.top());
    gl.glVertex3f(0, 1, 0);
    gl.glEnd();
    texture.disable(gl);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glViewport(0,0,width,height);
    gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
    repaint();
  }

  public static FlatPanel getInstance() {
    return instance;
  }
//
//  public static void main(String[] args) {
//    JFrame frame = new JFrame();
//    frame.setVisible(true);
//    frame.setSize(500,500);
//    FlatPanel panel = FlatPanel.getInstance();
//    try {
//      panel.setImage(ImageIO.read(new File("D:\\ImageMe\\three_d.jpg")));
//    }catch (IOException e) {
//      e.printStackTrace();
//    }
//    frame.add(panel);
//  }
}
