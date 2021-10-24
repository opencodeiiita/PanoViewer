package PanoViewer;

import org.joml.Matrix4f;
import org.joml.Vector3f;  //Holds a 3-tuple vector.

/**
 * A fixed camera located at the (0,0,0).
 *
 * @author kshan
 */
public class Camera {

  /*Position of Camera */
  public final Vector3f pos;
  /* Camera target which stays on a sphere of radius 1. */
  public final Vector3f target;
  /* Pitch axis of camera. Initially Pointing towards +ve X axis. */
  public final Vector3f pitchAxis;
  /* Yaw axis of camera and up vector for calculating perspective matrix.
   Initially towards +ve Y axis.*/
  public final Vector3f up;
  /* Perspective matrix generated from this camera's orientation. */
  public final Matrix4f lookAtMat;
  /* Yaw of this camera. Value should be between 0 and 2*pi. */
  //yaw is a counterclockwise rotation of alpha about the z-axis.
  public double yaw = 0;
  /* Pitch of this camera. Value should be between -pi/2 and pi/2. */
  //A pitch is a counterclockwise rotation of beta about the y-axis.
  public double pitch = 0;

  
  public Camera() {
    pos = new Vector3f(0, 0, 0);
    target = new Vector3f(0, 0, -1);
    up = new Vector3f(0, 1, 0);
    pitchAxis = new Vector3f(1, 0, 0);
    lookAtMat = new Matrix4f();
    updateViewMatrix();    
    //System.out.println(lookAtMat.setLookAt(pos, up ,target));
    //System.out.println("Camera line 34:");
  }
/**
 * Changes the target and normalizes it to unit matrix.
 * @param newTarget 
 */
  public void changeTarget(Vector3f newTarget) {
    target.set(newTarget.normalize());
    updateViewMatrix();  //lookAtMat.setLookAt(pos, up ,target);
    //System.out.println("changeTarget line 48:");
    //System.out.println(lookAtMat.setLookAt(pos, up ,target));
  }
/**
 * 
 * @return the current view orientation in.
 */
  public Matrix4f getViewMatrix() {
    return lookAtMat;
    //System.out.println(getViewMatrix());
    //System.out.println("UpdateViewMatrix line 58:");
  }
/**
 * updates the orientation to (pos(x),up(y),target(z)) matrix.
 */
  public void updateViewMatrix() {
    lookAtMat.setLookAt(pos, up ,target);
//    System.out.println("UpdateViewMatrix line 60:");
//    System.out.println(lookAtMat);
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
    //System.out.println("rotate line 79:");
    //System.out.println(lookAtMat.setLookAt(pos, up ,target));
  }
/**
 * rotates camera towards cross product of direction matrix.
 * @param deltaYaw 
 */
  public void rotateY(float deltaYaw) {
    target.rotateY(deltaYaw);
    up.rotateY(deltaYaw);
    pitchAxis.rotateY(deltaYaw);
    yaw += deltaYaw;  //yaw+ deltaYaw
    yaw = yaw > 2 * Math.PI ? yaw - 2 * Math.PI : yaw < 0 ? yaw + 2 * Math.PI : yaw;
                               //deltaYaw           (back in range  0,2pi)     else yaw
    //System.out.println(pitchAxis.rotateY(deltaYaw));
    //System.out.println("rotateY line 90:");
    //System.out.println(yaw);
    //System.out.println(lookAtMat.setLookAt(pos, up ,target));
    
  }                           
  /**
   * rotates up & target vector along the pitch axis in clockwise direction.
   * @param deltaPitch the angle to rotate by in radians.
   */
  public void rotateAlongPitchAxis(float deltaPitch) {
    if (pitch + deltaPitch > Math.PI / 2) {
      deltaPitch = (float) (Math.PI / 2 - pitch);
    }
    if (pitch + deltaPitch < -Math.PI / 2) {
      deltaPitch = (float) (-Math.PI / 2 - pitch);
    }
    pitch += deltaPitch;
//    System.out.println("pitch");
//    System.out.println(pitch);
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
 * @return current pitch direction.
 */
  public double getPitch() {
    return pitch;
  }
/**
 * it resets the orientation and target to below assigned values. 
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
   * changes camera orientation 90 degree upwards.
   * @param args
   */
  public static void main (String[] args){
    Camera camera = new Camera();
    camera.rotate(0, (float) (Math.PI/2));
    System.out.println(camera.up);
  }

}
