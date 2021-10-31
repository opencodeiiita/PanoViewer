/*
 *
 */
package PanoViewer;

import com.jogamp.opengl.GLProfile;

/**
 *
 * @author kshan
 */
public class Settings {

  private static float dragSensitivity = 1f;
  private static int wheelSensitivity = 5;
  private static int precision = 180;
  private static GLProfile gl;
  /* Whether or not to flip the image when creating texture data. */
  private static boolean invertImage;

  private Settings() {
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

  public static float getDragSensitivity() {
    return dragSensitivity;
  }

  public static void setDragSensitivity(float newSensi) {
    dragSensitivity = newSensi;
  }

  public static int getWheelSensitivity() {
    return wheelSensitivity;
  }

  public static void setWheelSensitivity(int newSensi) {
    wheelSensitivity = newSensi;
  }

  public static int getPrecision() {
    return precision;
  }

  public static void setPrecision(int newPrecision) {
    precision = newPrecision;
  }
}
