/*
 * 
 */
package PanoViewer.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import static PanoViewer.settings.dragSenstivity;
import static PanoViewer.settings.wheelSenstivity;

/**
 *
 * @author kshan
 */
public abstract class ZoomPanLis implements MouseListener, MouseMotionListener, MouseWheelListener {

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
    double yaw = Math.PI * (newX -lastX ) / width * dragSenstivity;
    double pitch = Math.PI * (lastY - newY) / height * dragSenstivity;
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
    zoom(e.getWheelRotation()* wheelSenstivity);
  }

  abstract void zoom(int zoomAmount);
}
