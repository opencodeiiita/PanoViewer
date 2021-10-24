/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
//package PanoViewer;

import PanoViewer.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;  
//import PanoViewer.Camera.rotateY;
//import PanoViewer.rotate;
//import PanoViewer.rotateAlongPitchAxis;
//import PanoViewer.updateViewMatrix;
//import org.joml.Matrix4f;
//import org.joml.Matrix3f;
//import org.joml.Vector3f;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.joml.sampling;
/**
 *
 * @author Kirtee
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
      double yaw = rotate_new.getYaw();
      assertEquals(true, yaw>=0 && yaw<=2*Math.PI);
   }
      
   @Test
   public void rotateAlongPitchAxis_test(){
     Camera rotatealongpitchaxis = new Camera();
     double outputaxis = rotatealongpitchaxis.getPitch();
     assertEquals(0.0,outputaxis,0.0001);
   }

   @Test
   public void rotate_test(){
     Camera rotate_test = new Camera();
     //double pitch = rotate_test.getPitch();   
     assertEquals(false, rotate_test.getPitch() >= Math.PI/2 && rotate_test.getPitch()<-Math.PI/2);
       
   }
}
//   @Test
//   public Matrix4f getViewMatrix_test(){
//     Camera getmatrix_test = new Camera();
////     double yaw =1;
////     Vector3f pitchAxis = null;
////     Vector3f target = null;
////     Vector3f up = null;
////     Vector3f pos = new Vector3f(2, 0, 0);
////     target.set(0, 1, -1);
////     up.set(0, -1, 0);
////     pitchAxis.set(1, 0, 0);
//     Matrix4f lookAtMat = null;
//     //lookAtMat.setLookAt((2, 0 ,0),(0, -1, 0) ,(0,1,-1));
//     Matrix4f output = getmatrix_test.lookAtMat;
//     assertEquals(true),output);
//   
//    }
//}  