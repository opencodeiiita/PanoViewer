/*
 * 
 */
package PanoViewer.gui;

import PanoViewer.Camera;
import static PanoViewer.Utils.joglUtils.createShaderProgram;
import static PanoViewer.Utils.joglUtils.loadTextureAWT;
import PanoViewer.math.Sphere;
import com.jogamp.common.nio.Buffers;
import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_FRONT_AND_BACK;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_LINEAR_MIPMAP_LINEAR;
import static com.jogamp.opengl.GL.GL_STATIC_DRAW;
import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL2GL3.GL_LINE;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author kshan
 */
public class PhotoSphere extends GLCanvas implements GLEventListener {

  private final Sphere sphere;
  private final int vao[] = new int[1];
  private final int vbo[] = new int[2];
  private int rendering_program;
  private final Camera camera;
  private final FloatBuffer vals = Buffers.newDirectFloatBuffer(16);
  private Matrix4f pMat = new Matrix4f();
  private Matrix4f vMat = new Matrix4f();
  private final Matrix4f mvMat = new Matrix4f();
  private final Matrix4f mMat = new Matrix4f();
  private int mvLoc, projLoc;
  private float aspect;
  private int texId;
  private final BufferedImage image;
  private final Vector3f sphereLoc;
  private int numVerts;
  private final ZoomPanLis listener;
  private final PhotoSphere instance;
  private int fov;
  private static final int MAX_FOV = 110;
  private static final int MIN_FOV = 20;
  private static final int IDEAL_FOV = 90;

  public PhotoSphere(BufferedImage img, int prec) {
    addGLEventListener(this);
    this.image = img;
    instance = this;
    sphere = new Sphere(prec);
    camera = new Camera();
    sphereLoc = new Vector3f(0, 0, 0);
    FPSAnimator animtr = new FPSAnimator(this, 30);
    animtr.start();
    listener = new ZoomPanLis() {
      @Override
      void rotate(double yaw, double pitch) {
        float newYaw = (float) (yaw * fov / IDEAL_FOV);
        float newPitch = (float) (pitch * fov / IDEAL_FOV);
        camera.rotate(newYaw, newPitch);
        vMat = camera.getViewMatrix();
        instance.repaint();
      }

      @Override
      void zoom(int zoomAmount) {
        fov += zoomAmount;
        fov = Math.min(fov, MAX_FOV);
        fov = Math.max(fov, MIN_FOV);
        pMat.setPerspective((float) Math.toRadians(fov), aspect, 0.1f, 1000.0f);
        instance.repaint();
      }
    };
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    this.addMouseWheelListener(listener);
    fov = IDEAL_FOV;
  }

  @Override
  public void init(GLAutoDrawable glad) {
    rendering_program = createShaderProgram("Shaders/vertex.shader", "Shaders/frag.shader");
    setupVertices();
    aspect = (float) getWidth() / (float) getHeight();
    pMat.setPerspective((float) Math.toRadians(70), aspect, 0.1f, 1000.0f);
    texId = loadTextureAWT(image);
    vMat = camera.getViewMatrix();
    mMat.translation(sphereLoc);
  }

  @Override
  public void dispose(GLAutoDrawable glad) {
  }

  @Override
  public void display(GLAutoDrawable glad) {

    GL4 gl = (GL4) GLContext.getCurrentGL();
    gl.glClear(GL_DEPTH_BUFFER_BIT);
    gl.glClear(GL_COLOR_BUFFER_BIT);
    gl.glUseProgram(rendering_program);
    mvLoc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
    projLoc = gl.glGetUniformLocation(rendering_program, "proj_matrix");
//    mMat.rotate(500*x, 1000 * x, 0);
// concatenate model and view matrix to create MV matrix
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

    gl.glActiveTexture(GL_TEXTURE0);
    gl.glGenerateMipmap(GL_TEXTURE_2D);
    gl.glBindTexture(GL_TEXTURE_2D, texId);

    gl.glEnable(GL_DEPTH_TEST);
    gl.glDepthFunc(GL_LEQUAL);
//    gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    gl.glDrawArrays(GL_TRIANGLES, 0, numVerts);
  }

  @Override
  public void reshape(GLAutoDrawable glad, int x, int y, int widht, int height) {
    /*http://forum.jogamp.org/canvas-not-filling-frame-td4040092.html#a4040138*/
    GL4 gl = GLContext.getCurrentGL().getGL4();
    double dpiScalingFactor = ((Graphics2D) getGraphics()).getTransform().getScaleX();
    widht = (int) (widht * dpiScalingFactor);
    height = (int) (height * dpiScalingFactor);
    gl.glViewport(0, 0, widht, height);
  }

  private void setupVertices() {
    GL4 gl = GLContext.getCurrentGL().getGL4();
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
    gl.glBufferData(GL_ARRAY_BUFFER, vertBuffer.limit() * 4, vertBuffer, GL_STATIC_DRAW);

    gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
    FloatBuffer texBuff = Buffers.newDirectFloatBuffer(texValue);
    gl.glBufferData(GL_ARRAY_BUFFER, texBuff.limit() * 4, texBuff, GL_STATIC_DRAW);
  }

  void rotate(double theta, double phi) {
  }
}
