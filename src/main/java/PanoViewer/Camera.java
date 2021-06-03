/*
 * 
 */
package PanoViewer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A fixed camera located at the center of PhotoSphere.
 *
 * @author kshan
 */
public class Camera {

  private final Vector3f pos;
  private final Vector3f target;
  private final Vector3f pitchAxis;
  private final Vector3f up;
  private final Matrix4f lookAtMat;
  private float yaw = 0;
  private float pitch = 0;

  public Camera() {
    pos = new Vector3f(0, 0, 0);
    target = new Vector3f(0, 0, -1);
    up = new Vector3f(0, 1, 0);
    pitchAxis = new Vector3f(1, 0, 0);
    lookAtMat = new Matrix4f();
    updateViewMatrix();
  }

  public void changeTarget(Vector3f target) {
    this.target.set(target);
    updateViewMatrix();
  }

  public Matrix4f getViewMatrix() {
    return lookAtMat;
  }

  private void updateViewMatrix() {
    lookAtMat.setLookAt(pos, target, up);
  }

  public void rotate(float yaw, float pitch) {
    rotateY(yaw);
    rotateX(pitch);
    updateViewMatrix();
  }

  private void rotateY(float yaw) {
    this.yaw += yaw;
    target.rotateY(yaw);
    up.rotateY(yaw);
    pitchAxis.rotateY(yaw);
  }

  private void rotateX(float pitch) {
    if (this.pitch + pitch > Math.PI / 2) {
      pitch = (float) (Math.PI / 2 - this.pitch);
    }
    if (this.pitch + pitch < -Math.PI / 2) {
      pitch = (float) (-Math.PI / 2 - this.pitch);
    }
    this.pitch += pitch;
    target.rotateAxis(pitch, pitchAxis.x(), pitchAxis.y(), pitchAxis.z());
    up.rotateAxis(pitch, pitchAxis.x(), pitchAxis.y(), pitchAxis.z());
  }

  public float getYaw() {
    return yaw;
  }

  public float getPitch() {
    return pitch;
  }
}
