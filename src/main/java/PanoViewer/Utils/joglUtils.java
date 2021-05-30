/*
 * 
 */
package PanoViewer.Utils;

import static PanoViewer.Utils.IOUtils.getBufferedImage;
import static PanoViewer.Utils.IOUtils.getFileFromResourceAsStream;
import static PanoViewer.Utils.imageutils.getRGBAPixelData;
import com.jogamp.common.nio.Buffers;
import static com.jogamp.opengl.GL.GL_LINEAR;
import static com.jogamp.opengl.GL.GL_LINEAR_MIPMAP_LINEAR;
import static com.jogamp.opengl.GL.GL_LINEAR_MIPMAP_NEAREST;
import static com.jogamp.opengl.GL.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT;
import static com.jogamp.opengl.GL.GL_NEAREST;
import static com.jogamp.opengl.GL.GL_NEAREST_MIPMAP_NEAREST;
import static com.jogamp.opengl.GL.GL_NO_ERROR;
import static com.jogamp.opengl.GL.GL_RGBA;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAX_ANISOTROPY_EXT;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_UNSIGNED_BYTE;
import static com.jogamp.opengl.GL2ES2.GL_COMPILE_STATUS;
import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_INFO_LOG_LENGTH;
import static com.jogamp.opengl.GL2ES2.GL_LINK_STATUS;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.glu.GLU;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author kshan
 */
public class joglUtils {

  public static int loadTextureAWT(String textureFileName) {
    BufferedImage textureImage = getBufferedImage(textureFileName);
    return loadTextureAWT(textureImage);
  }

  public static int loadTextureAWT(BufferedImage textureImage) {
    GL4 gl = (GL4) GLContext.getCurrentGL();
    byte[] imgRGBA = getRGBAPixelData(textureImage);
    ByteBuffer rgbaBuffer = Buffers.newDirectByteBuffer(imgRGBA);
    int[] textureIDs = new int[1]; // array to hold generated texture IDs
    gl.glGenTextures(1, textureIDs, 0);
    int textureID = textureIDs[0]; // ID for the 0th texture object
    gl.glBindTexture(GL_TEXTURE_2D, textureID); // specifies the active 2D texture
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    gl.glGenerateMipmap(GL_TEXTURE_2D);
    if (gl.isExtensionAvailable("GL_EXT_texture_filter_anisotropic")) {
      float anisoSetting[] = new float[1];
      gl.glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, anisoSetting, 0);
      gl.glTextureParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, anisoSetting[0]);
    }
    gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, // MIPMAP level, color space
            textureImage.getWidth(), textureImage.getHeight(), 0, // image size, border (ignored)
            GL_RGBA, GL_UNSIGNED_BYTE, // pixel format and data type 
            rgbaBuffer
    ); // buffer holding texture data
    return textureID;
  }

  public static String[] readShaderSource(String filename) {

    Vector<String> lines = new Vector<String>();
    Scanner sc;
    sc = new Scanner(getFileFromResourceAsStream(filename));
    while (sc.hasNext()) {
      lines.addElement(sc.nextLine());
    }
    String[] program = new String[lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      program[i] = (String) lines.elementAt(i) + "\n";
    }
    return program;
  }

  public static int createShaderProgram(String vFileName, String fFileName) {
    int[] vertCompiled = new int[1];
    int[] fragCompiled = new int[1];
    int[] linked = new int[1];
    GL4 gl = (GL4) GLContext.getCurrentGL();
    String[] vshaderSource = readShaderSource(vFileName);
    String[] fshaderSource = readShaderSource(fFileName);
    int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
    gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
    gl.glCompileShader(vShader);
    checkOpenGLError(); // can use returned boolean
    gl.glGetShaderiv(vShader, GL_COMPILE_STATUS, vertCompiled, 0);
    if (vertCompiled[0] == 1) {
      System.out.println("vShader vertex compilation success.");
    } else {
      System.out.println("vShader vertex compilation failed.");
      printShaderLog(vShader);
    }

    int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);
    gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);
    gl.glCompileShader(fShader);
    checkOpenGLError(); // can use returned boolean
    gl.glGetShaderiv(fShader, GL_COMPILE_STATUS, fragCompiled, 0);
    if (fragCompiled[0] == 1) {
      System.out.println("fShader fragment compilation success.");
    } else {
      System.out.println("fShader fragment compilation failed.");
      printShaderLog(fShader);
    }

    if ((vertCompiled[0] != 1) || (fragCompiled[0] != 1)) {
      System.out.println("\nCompilation error; return-flags:");
      System.out.println(" vertCompiled = " + vertCompiled[0]
              + "fragCompiled =  " + fragCompiled[0]);
    } else {
      System.out.println("Successful compilation");
    }

    int vfprogram = gl.glCreateProgram();
    gl.glAttachShader(vfprogram, vShader);
    gl.glAttachShader(vfprogram, fShader);
    gl.glLinkProgram(vfprogram);

    checkOpenGLError();
    gl.glGetProgramiv(vfprogram, GL_LINK_STATUS, linked, 0);
    if (linked[0] == 1) {
      System.out.println("vfprogram linking succeeded.");
    } else {
      System.out.println("vfprogram linking failed.");
      printProgramLog(vfprogram);
    }

    gl.glDeleteShader(vShader);
    gl.glDeleteShader(fShader);
    return vfprogram;
  }

  static private void printShaderLog(int shader) {
    GL4 gl = (GL4) GLContext.getCurrentGL();
    int[] len = new int[1];
    int[] chWrittn = new int[1];
    byte[] log = null;
    gl.glGetShaderiv(shader, GL_INFO_LOG_LENGTH, len, 0);
    if (len[0] > 0) {
      log = new byte[len[0]];
      gl.glGetShaderInfoLog(shader, len[0], chWrittn, 0, log, 0);
      System.out.println("Shader Info Log: ");
      for (int i = 0; i < log.length; i++) {
        System.out.print((char) log[i]);
      }
    }
  }

  static private void printProgramLog(int program) {
    GL4 gl = (GL4) GLContext.getCurrentGL();
    int[] len = new int[1];
    int[] chWrittn = new int[1];
    byte[] log = null;
    gl.glGetShaderiv(program, GL_INFO_LOG_LENGTH, len, 0);
    if (len[0] > 0) {
      log = new byte[len[0]];
      gl.glGetShaderInfoLog(program, len[0], chWrittn, 0, log, 0);
      System.out.println("Shader Info Log: ");
      for (int i = 0; i < log.length; i++) {
        System.out.print((char) log[i]);
      }
    }
  }

  static boolean checkOpenGLError() {
    GL4 g = (GL4) GLContext.getCurrentGL();
    boolean foundError = false;
    GLU glu = new GLU();
    int glErr = g.glGetError();
    while (glErr != GL_NO_ERROR) {
      System.out.println("glError " + glu.gluErrorString(glErr));
      foundError = true;
      glErr = g.glGetError();
    }
    return foundError;
  }
}
