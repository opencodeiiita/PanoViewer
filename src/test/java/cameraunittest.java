/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import PanoViewer.Camera;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 *
 */
public class cameraunittest {

  public cameraunittest() {}


  @Test
  public void Camera_test(){
    Camera camera_new= new Camera();
    double epsilon = 0.00001;
    assertEquals(true,camera_new.getYaw()-(0)<epsilon);
  }

   @Test
   public void rotateY_test(){
      Camera rotate_new = new Camera();
      rotate_new.rotateY(3);
      double yaw = rotate_new.getYaw();
      assertEquals(true, yaw>=0 && yaw<=2*Math.PI);
   }

   @Test
   public void rotateAlongPitchAxis_test(){
     Camera rotatealongpitchaxis = new Camera();
     rotatealongpitchaxis.rotateAlongPitchAxis(9);
     double outputaxis = rotatealongpitchaxis.getPitch();
     assertEquals(1.57,outputaxis,0.001);
   }

   @Test
   public void rotate_test(){
     Camera rotate_test = new Camera();
     rotate_test.rotate(-7,2);
     assertEquals(false, rotate_test.getPitch() >= Math.PI/2 && rotate_test.getPitch()<-Math.PI/2);

   }
}
