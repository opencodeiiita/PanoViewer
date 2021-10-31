package PanoViewer.ImagePanels;

import PanoViewer.Camera;
import PanoViewer.gui.JOGLImageViewer;
import PanoViewer.math.Sphere;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.nio.FloatBuffer;
import static PanoViewer.Settings.*;
import static PanoViewer.Utils.joglUtils.createShaderProgram;
import static PanoViewer.Utils.joglUtils.getTextureData;
import static com.jogamp.opengl.GL.*;

/**
 * Panoramic Panel which displays Panoramic Images
 *
 * @author Rohan Babbar
 */
public class PanoramicPanel extends JOGLImageViewer {

  private final int[] vao = new int[1];
  private final int[] vbo = new int[2];
  private int rendering_program;
  private final Camera camera;
  private final FloatBuffer vals = Buffers.newDirectFloatBuffer(16);
  private final Matrix4f pMat = new Matrix4f();
  private final Matrix4f vMat = new Matrix4f();
  private final Matrix4f mvMat = new Matrix4f();
  private final Matrix4f mMat = new Matrix4f();
  private int mvLoc, projLoc;
  private float aspect;
  private final Vector3f sphereLoc;
  private int numVerts;
  private int fov;
  private static final int MAX_FOV = 110;
  private static final int MIN_FOV = 20;
  private static final int IDEAL_FOV = 90;
  private Texture texture;
  private TextureData textureData;
  private boolean updateImage;
  private static PanoramicPanel instance;
  private boolean zoomEnable;
  private boolean panningEnable;
  private HandleMouseEvent event;

  private PanoramicPanel() {
    camera = new Camera();
    sphereLoc = new Vector3f(0, 0, 0);
    zoomEnable = true;
    panningEnable = true;
    event = new HandleMouseEvent();
    addMouseListener(event);
    addMouseMotionListener(event);
    addMouseWheelListener(event);
  }

  public static PanoramicPanel getInstance() {
    if (instance == null) {
      instance = new PanoramicPanel();
    }
    return instance;
  }

  @Override
  public void setImage(BufferedImage image) {
    textureData = getTextureData(image);
    updateImage = true;
    repaint();
    camera.reset();
    fov = IDEAL_FOV;
  }

  @Override
  public boolean isPanningEnabled() {
    return panningEnable;
  }

  @Override
  public void enablePanning(boolean enable) {
    if (enable) {
      addMouseMotionListener(event);
    } else {
      removeMouseMotionListener(event);
    }
    this.panningEnable = enable;
  }

  @Override
  public void pan(float panX, float panY) {
    float newYaw = (panX * fov / IDEAL_FOV);
    float newPitch = (panY * fov / IDEAL_FOV);
    camera.rotate(newYaw, newPitch);
    vMat.set(camera.getViewMatrix());
    repaint();
  }

  @Override
  public void enableZoom(boolean enable) {
    if (enable) {
      addMouseWheelListener(event);
    } else {
      removeMouseWheelListener(event);
    }
    this.zoomEnable = enable;
  }

  @Override
  public boolean isZoomEnabled() {
    return zoomEnable;
  }

  @Override
  public void zoom(float zoomBy) {
    fov += (int) zoomBy;
    fov = Math.min(fov, MAX_FOV);
    fov = Math.max(fov, MIN_FOV);
    pMat.setPerspective((float) Math.toRadians(fov), aspect, 0.1f, 1000.0f);
    repaint();
  }

  @Override
  public void init(GLAutoDrawable glad) {
    rendering_program = createShaderProgram("Shaders/vertex.shader", "Shaders/frag.shader");
    setupVertices(glad.getGL().getGL4());
    aspect = (float) getWidth() / (float) getHeight();
    pMat.setPerspective((float) Math.toRadians(fov), aspect, 0.1f, 1000.0f);
    vMat.set(camera.getViewMatrix());
    mMat.translation(sphereLoc);
    texture = new Texture(GL_TEXTURE_2D);
  }

  public void setupVertices(GL4 gl) {
    Sphere sphere = new Sphere(getPrecision());
    numVerts = sphere.getIndices().length;
    int[] indices = sphere.getIndices();
    Vector3f[] vertices = sphere.getVertices();
    Vector2f[] texCoords = sphere.getTexCoords();

    float[] points = new float[indices.length * 3];
    float[] texValue = new float[indices.length * 2];

    for (int i = 0; i < indices.length; i++) {
      points[3 * i] = vertices[indices[i]].x();
      points[3 * i + 1] = vertices[indices[i]].y();
      points[3 * i + 2] = vertices[indices[i]].z();

      texValue[2 * i] = texCoords[indices[i]].x();
      texValue[2 * i + 1] = texCoords[indices[i]].y();
    }
    gl.glGenBuffers(vao.length, vao, 0);
    gl.glBindVertexArray(vao[0]);
    gl.glGenBuffers(vbo.length, vbo, 0);

    gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
    FloatBuffer vertBuffer = Buffers.newDirectFloatBuffer(points);
    gl.glBufferData(GL_ARRAY_BUFFER, vertBuffer.limit() * 4L, vertBuffer, GL_STATIC_DRAW);

    gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
    FloatBuffer texBuff = Buffers.newDirectFloatBuffer(texValue);
    gl.glBufferData(GL_ARRAY_BUFFER, texBuff.limit() * 4L, texBuff, GL_STATIC_DRAW);
  }

  @Override
  public void dispose(GLAutoDrawable glad) {
    GL4 gl = glad.getGL().getGL4();
    gl.glDeleteProgram(rendering_program);
    gl.glDeleteVertexArrays(vao.length, vao, 0);
    gl.glDeleteBuffers(vbo.length, vbo, 0);
    texture.destroy(gl);
  }

  @Override
  public void display(GLAutoDrawable glad) {
    GL4 gl = glad.getGL().getGL4();
    if (updateImage) {
      texture.updateImage(gl, textureData);
      if (gl.isExtensionAvailable("GL_EXT_texture_filter_anisotropic")) {
        float max[] = new float[1];
        gl.glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, max, 0);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, max[0]);
      }
    }
    gl.glClear(GL_DEPTH_BUFFER_BIT);
    gl.glClear(GL_COLOR_BUFFER_BIT);
    gl.glUseProgram(rendering_program);
    mvLoc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
    projLoc = gl.glGetUniformLocation(rendering_program, "proj_matrix");
    pMat.setPerspective((float) Math.toRadians(fov), aspect, 0.1f, 1000.0f);

    mvMat.identity();
    mvMat.mul(vMat);
    mvMat.mul(mMat);

    gl.glUniformMatrix4fv(mvLoc, 1, false, mvMat.get(vals));
    gl.glUniformMatrix4fv(projLoc, 1, false, pMat.get(vals));

    gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
    gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    gl.glEnableVertexAttribArray(0);

    gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
    gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
    gl.glEnableVertexAttribArray(1);

    gl.glEnable(GL_DEPTH_TEST);
    gl.glDepthFunc(GL_LEQUAL);

    gl.glActiveTexture(GL_TEXTURE0);
    texture.enable(gl);
    texture.bind(gl);
    gl.glDrawArrays(GL_TRIANGLES, 0, numVerts);
    texture.disable(gl);
  }

  @Override
  public void reshape(GLAutoDrawable glad, int x, int y, int width, int height) {
    /* Resize haack required for java8 + GLCanvas.
    http://forum.jogamp.org/canvas-not-filling-frame-td4040092.html#a4040138*/
//    GL4 gl = glad.getGL().getGL4();
//    double dpiScalingFactor = ((Graphics2D) getGraphics()).getTransform().getScaleX();
//    width = (int) (width * dpiScalingFactor);
//    height = (int) (height * dpiScalingFactor);
//    gl.glViewport(0, 0, width, height);
//    aspect = (float) getWidth() / (float) getHeight();
//    pMat.setPerspective((float) Math.toRadians(70), aspect, 0.1f, 1000.0f);
  }

  /**
   *
   * Handles Mouse Events for Panning and Zooming
   */
  private class HandleMouseEvent extends MouseAdapter {

    private int finalX;
    private int finalY;

    public HandleMouseEvent() {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      zoom(e.getWheelRotation() * getWheelSensitivity());
    }

    @Override
    public void mousePressed(MouseEvent e) {
      finalX = e.getX();
      finalY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      int newX = e.getX();
      int newY = e.getY();
      int width = e.getComponent().getWidth();
      int height = e.getComponent().getHeight();
      double yaw = Math.PI * (newX - finalX) / width * getDragSensitivity();
      double pitch = Math.PI * (finalY - newY) / height * getDragSensitivity();
      pan((float) yaw, (float) pitch);
      finalX = newX;
      finalY = newY;
    }
  }
}
