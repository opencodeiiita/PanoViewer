/*
 * 
 */
package PanoViewer;

import com.jogamp.opengl.GLProfile;

/**
 *
 * @author kshan
 */
public class settings {

  public static float dragSenstivity = 1f;
  public static int wheelSenstivity = 5;
  public static int precision = 90;
  private static GLProfile gl;

  static {
    gl = GLProfile.getMaxProgrammable(true);
  }

  static GLProfile getMaxProfile() {
    return gl;
  }

  static boolean checkMinimumVersion() {
    return GLProfile.isAvailable(GLProfile.GL3);
  }
}
