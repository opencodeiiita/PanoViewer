/*
 * 
 */
package PanoViewer.gui;

import static PanoViewer.settings.senstivity;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author kshan
 */
public abstract class ZoomPanLis implements MouseListener, MouseMotionListener {

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
    double yaw = Math.PI * (newX -lastX ) / width * senstivity;
    double pitch = Math.PI * (lastY - newY) / height * senstivity;
    rotate(yaw, pitch);
    lastX = newX;
    lastY = newY;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  abstract void rotate(double theta, double phi);
}
