/*
 * 
 */
package PanoViewer.math;

import java.awt.geom.Point2D;
import static java.lang.Math.*;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author kshan
 */
public class Sphere {

  private int numVertices, numIndices, prec; // prec = precision
  private int[] indices;
  private Vector3f[] vertices;
  private Vector2f[] texCoords;

  public Sphere(int p) {
    prec = p;
    initSphere();
  }

  private void initSphere() {
    numVertices = (prec + 1) * (prec + 1);
    numIndices = prec * prec * 6;
    vertices = new Vector3f[numVertices];
    indices = new int[numIndices];
    texCoords = new Vector2f[numVertices];
    for (int i = 0; i < numVertices; i++) {
      vertices[i] = new Vector3f();
      texCoords[i] = new Vector2f();
    }
// calculate triangle vertices
    for (int i = 0; i <= prec; i++) {
      for (int j = 0; j <= prec; j++) {
        float y = (float) cos(toRadians(180 - i * 180 / prec));
        float x = -(float) cos(toRadians(j * 360 / prec)) * (float) abs(cos(asin(y)));
        float z = (float) sin(toRadians(j * 360 / prec)) * (float) abs(cos(asin(y)));
        vertices[i * (prec + 1) + j].set(x, y, z);
        texCoords[i * (prec + 1) + j].set((float) j / prec, (float) i / prec);
      }
    }
// calculate triangle indices
    for (int i = 0; i < prec; i++) {
      for (int j = 0; j < prec; j++) {
        indices[6 * (i * prec + j) + 0] = i * (prec + 1) + j;
        indices[6 * (i * prec + j) + 1] = i * (prec + 1) + j + 1;
        indices[6 * (i * prec + j) + 2] = (i + 1) * (prec + 1) + j;
        indices[6 * (i * prec + j) + 3] = i * (prec + 1) + j + 1;
        indices[6 * (i * prec + j) + 4] = (i + 1) * (prec + 1) + j + 1;
        indices[6 * (i * prec + j) + 5] = (i + 1) * (prec + 1) + j;
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
