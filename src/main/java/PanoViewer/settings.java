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

  private static float dragSenstivity = 1f;
  private static int wheelSenstivity = 5;
  private static int precision = 90;
  private static GLProfile gl;
  /* Whether or not to flip the image when creating texture data. */
  private static boolean invertImage;

  private settings() {
  }

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

  public static boolean checkMinimumVersion() {
    return GLProfile.isAvailable(GLProfile.GL4);
  }

  public static float getDragSenstivity() {
    return dragSenstivity;
  }

  public static void setDragSenstivity(float newSensi) {
    dragSenstivity = newSensi;
  }

  public static int getWheelSenstivity() {
    return wheelSenstivity;
  }

  public static void setWheelSenstivity(int newSensi) {
    wheelSenstivity = newSensi;
  }

  public static int getPrecision() {
    return precision;
  }

  public static void setPrecision(int newPrecision) {
    precision = newPrecision;
  }
}
