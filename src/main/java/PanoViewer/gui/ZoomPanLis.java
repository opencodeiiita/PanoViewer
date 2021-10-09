/*
 *
 */
package PanoViewer.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import static PanoViewer.Settings.*;
import java.awt.event.MouseAdapter;

/**
 *
 * @author kshan
 */
public abstract class ZoomPanLis extends MouseAdapter {

  private int lastX;
  private int lastY;

  public ZoomPanLis() {

  }
  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
    lastX = e.getX();
    lastY = e.getY();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    int newX = e.getX();
    int newY = e.getY();
    int width = e.getComponent().getWidth();
    int height = e.getComponent().getHeight();
    double yaw = Math.PI * (newX -lastX ) / width * getDragSensitivity();
    double pitch = Math.PI * (lastY - newY) / height * getDragSensitivity();
    rotate(yaw, pitch);
    lastX = newX;
    lastY = newY;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  abstract void rotate(double yaw, double pitch);

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    zoom(e.getWheelRotation()* getWheelSensitivity());
  }

  abstract void zoom(int zoomAmount);
}
