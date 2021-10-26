package PanoViewer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A fixed camera located at the (0,0,0).
 *
 * @author kshan
 */
public class Camera {

  /*Position of Camera */
  private final Vector3f pos;
  /* Camera target which stays on a sphere of radius 1. */
  private final Vector3f target;
  /* Pitch axis of camera. Initially Pointing towards +ve X axis. */
  private final Vector3f pitchAxis;
  /* Yaw axis of camera and up vector for calculating perspective matrix.
   Initially towards +ve Y axis.*/
  private final Vector3f up;
  /* Perspective matrix generated from this camera's orientation. */
  private final Matrix4f lookAtMat;
  /* Yaw of this camera. Value should be between 0 and 2*pi. */
  private double yaw = 0;
  /* Pitch of this camera. Value should be between -pi/2 and pi/2. */
  private double pitch = 0;


  public Camera() {
    pos = new Vector3f(0, 0, 0);
    target = new Vector3f(0, 0, -1);
    up = new Vector3f(0, 1, 0);
    pitchAxis = new Vector3f(1, 0, 0);
    lookAtMat = new Matrix4f();
    updateViewMatrix();
  }
/**
 * Changes the target and normalizes it to unit matrix.
 * @param newTarget
 */
  public void changeTarget(Vector3f newTarget) {
    target.set(newTarget.normalize());
    updateViewMatrix();
  }
/**
 *
 * @return the current view orientation in.
 */
  public Matrix4f getViewMatrix() {
    return lookAtMat;
  }
/**
 * updates the orientation to (pos(x),target(z),up(y)) matrix.
 */
  private void updateViewMatrix() {
    lookAtMat.setLookAt(pos, target, up);
  }
/**
 *
 * @param yaw
 * @param pitch
 */
  public void rotate(float yaw, float pitch) {
    rotateY(yaw);
    rotateAlongPitchAxis(pitch);
    updateViewMatrix();
  }
/**
 * rotates camera towards cross product of direction matrix.
 * @param deltaYaw
 */
  private void rotateY(float deltaYaw) {
    target.rotateY(deltaYaw);
    up.rotateY(deltaYaw);
    pitchAxis.rotateY(deltaYaw);
    yaw += deltaYaw;  //yaw+ deltaYaw
    yaw = yaw > 2 * Math.PI ? yaw - 2 * Math.PI : yaw < 0 ? yaw + 2 * Math.PI : yaw;
  }
  /**
   * rotates up & target vector along the pitch axis in clockwise direction.
   * @param deltaPitch the angle to rotate by in radians.
   */
  private void rotateAlongPitchAxis(float deltaPitch) {
    if (pitch + deltaPitch > Math.PI / 2) {
      deltaPitch = (float) (Math.PI / 2 - pitch);
    }
    if (pitch + deltaPitch < -Math.PI / 2) {
      deltaPitch = (float) (-Math.PI / 2 - pitch);
    }
    pitch += deltaPitch;
    target.rotateAxis(deltaPitch, pitchAxis.x(), pitchAxis.y(), pitchAxis.z());
    up.rotateAxis(deltaPitch, pitchAxis.x(), pitchAxis.y(), pitchAxis.z());
  }
/**
 *
 * @return the current yaw angle.
 */
  public double getYaw() {
    return yaw;
  }
/**
 *
 * @return current pitch angle.
 */
  public double getPitch() {
    return pitch;
  }
/**
 * It resets the orientation to target towards -ve z-axis.
 */
  public void reset() {
    yaw = 0;
    pitch = 0;
    target.set(0, 0, -1);
    up.set(0, 1, 0);
    pitchAxis.set(1, 0, 0);
    updateViewMatrix();
  }
  /**
   * Driver function to test camera class.
   * @param args
   */
  public static void main (String[] args){
    Camera camera = new Camera();
    camera.rotate(0, (float) (Math.PI/2));
    System.out.println(camera.up);
  }

}
