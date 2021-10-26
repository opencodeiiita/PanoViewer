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

//   @Test
//   public void rotateY_test(){
//      Camera rotate_new = new Camera();
//      rotate_new.rotate(3.0f, 6.0f);
//      double yaw = rotate_new.getYaw();
//      assertEquals(true, yaw>=0 && yaw<=2*Math.PI);
//   }
//
//   @Test
//   public void rotateAlongPitchAxis_test(){
//     Camera rotatealongpitchaxis = new Camera();
//     rotatealongpitchaxis.rotateAlongPitchAxis(9.6f,8.9f);
//     double outputaxis = rotatealongpitchaxis.getPitch();
//     assertEquals(1.57,outputaxis,0.001);
//   }

   @Test
   public void rotate_test(){
     Camera rotate_test = new Camera();
     double epsilon = 1e-5;
     double yaw = rotate_test.getYaw();

     rotate_test.rotate((float) (Math.PI/4), (float) (Math.PI/4));
     assertTrue(rotate_test.getYaw() <= 2*Math.PI+epsilon );
     assertTrue(rotate_test.getYaw() >= 0 -epsilon );
     assertEquals((float)(Math.PI/4),rotate_test.getYaw(),epsilon);
     assertTrue(Math.abs(rotate_test.getPitch()-Math.PI/4)<epsilon);

     rotate_test.rotate((float)(3*Math.PI/4), (float) (Math.PI/2));
     assertTrue(rotate_test.getYaw() <= 2*Math.PI+epsilon );
     assertTrue(rotate_test.getYaw() >= 0 -epsilon );
     assertTrue(rotate_test.getPitch() <= Math.PI/2+epsilon);
     assertTrue(rotate_test.getPitch() >= -Math.PI/2-epsilon );
     assertTrue(rotate_test.getYaw()+epsilon>=(float)(3/2*Math.PI));
     assertTrue(rotate_test.getYaw()-epsilon<=(float)(3/2*Math.PI));
     assertTrue(rotate_test.getPitch()+epsilon>=(float)(Math.PI/2));
     assertTrue(rotate_test.getPitch()-epsilon<=(float)(Math.PI/2));

     rotate_test.rotate((float) (4/3*Math.PI), (float) (7/4*Math.PI));
     assertTrue(rotate_test.getYaw() <= 2*Math.PI+epsilon );
     assertTrue(rotate_test.getYaw() >= 0 -epsilon );
     assertTrue(rotate_test.getYaw()+epsilon>=(float)(4/6*Math.PI));
     assertTrue(rotate_test.getYaw()-epsilon<=(float)(4/6*Math.PI));
     assertTrue(rotate_test.getPitch() <= Math.PI/2+epsilon );
     assertTrue(rotate_test.getPitch() >= -Math.PI/2-epsilon );



        }
}
