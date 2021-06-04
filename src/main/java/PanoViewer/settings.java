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
  private static boolean invertImage;

  private settings() {}

  static {
    gl = GLProfile.getMaxProgrammable(true);
  }

  public static void invertImage(boolean invert) {
    invertImage = invert;
  }

  public static boolean invertImage() {
    return invertImage;
  }
  static GLProfile getMaxProfile() {
    return gl;
  }

  static boolean checkMinimumVersion() {
    return GLProfile.isAvailable(GLProfile.GL3);
  }
}
