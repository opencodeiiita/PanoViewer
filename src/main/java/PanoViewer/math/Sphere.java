/*
 * 
 */
package PanoViewer.math;

import static PanoViewer.settings.invertImage;
import java.awt.geom.Point2D;
import static java.lang.Math.*;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author kshan
 */
public class Sphere {

  private int numVertices, numIndices, horizontalP; // prec = precision
  private int[] indices;
  private Vector3f[] vertices;
  private Vector2f[] texCoords;
  private final int verticalP;

  public Sphere(int width) {
    horizontalP = width;
    verticalP = horizontalP / 2;
    initSphere();
  }

  private void initSphere() {
    numVertices = (verticalP + 1) * (horizontalP + 1);
    numIndices = verticalP * horizontalP * 6;
    vertices = new Vector3f[numVertices];
    indices = new int[numIndices];
    double sliceAngle = Math.PI / verticalP;
    double sectorAngle = 2 * Math.PI / horizontalP;
    texCoords = new Vector2f[numVertices];
    for (int i = 0; i < numVertices; i++) {
      vertices[i] = new Vector3f();
      texCoords[i] = new Vector2f();
    }
// calculate triangle vertices
    for (int i = 0; i <= verticalP; i++) {
      double theta = Math.PI - i * sliceAngle;
      float y = (float) cos(Math.PI - i * sliceAngle);
      for (int j = 0; j <= horizontalP; j++) {
        float x = -(float) cos(j * sectorAngle) * (float) abs(sin(theta));
        float z = (float) sin(j * sectorAngle) * (float) abs(sin(theta));
        vertices[i * (horizontalP + 1) + j].set(x, y, z);
        /*
         * Horizontal inversion because images are viewed from inside. Vertical Inversion 
         * if texture image is not flipped.
         */
        x = 1 - (float) j / horizontalP;
        z = invertImage() ? ((float) i / verticalP) : (1 - (float) i / verticalP);
        texCoords[i * (horizontalP + 1) + j].set(x, z);
      }
    }
// calculate triangle indices
    for (int i = 0; i < verticalP; i++) {
      for (int j = 0; j < horizontalP; j++) {
        indices[6 * (i * horizontalP + j) + 0] = i * (horizontalP + 1) + j;
        indices[6 * (i * horizontalP + j) + 1] = i * (horizontalP + 1) + j + 1;
        indices[6 * (i * horizontalP + j) + 2] = (i + 1) * (horizontalP + 1) + j;
        indices[6 * (i * horizontalP + j) + 3] = i * (horizontalP + 1) + j + 1;
        indices[6 * (i * horizontalP + j) + 4] = (i + 1) * (horizontalP + 1) + j + 1;
        indices[6 * (i * horizontalP + j) + 5] = (i + 1) * (horizontalP + 1) + j;
      }
    }
  }

  public int[] getIndices() {
    return indices;
  }

  public int getNumIndices() {
    return numIndices;
  }

  public int getNumVertices() {
    return numVertices;
  }

  public Vector3f[] getVertices() {
    return vertices;
  }

  public Vector2f[] getTexCoords() {
    return texCoords;
  }
}
