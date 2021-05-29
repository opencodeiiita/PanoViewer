/*
 * 
 */
package PanoViewer.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kshan
 */
public class IOUtils {

  public static InputStream getFileFromResourceAsStream(String fileName) {

    // The class loader that loaded the class
    Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    ClassLoader classLoader = currentClass.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    // the stream holding the file content
    if (inputStream == null) {
      return null;
    } else {
      return inputStream;
    }

  }

  public static File getFileFromResource(String fileName) {
    File file = null;
    // The class loader that loaded the class
    Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    ClassLoader classLoader = currentClass.getClassLoader();
    URL resource = classLoader.getResource(fileName);
    try {
      file = new File(resource.toURI());
    } catch (URISyntaxException ex) {
      Logger.getLogger(currentClass.getName()).log(Level.SEVERE, null, ex);
    }
    return file;
  }

  public static BufferedImage getBufferedImage(String fileName) {
    BufferedImage img;
    try {
      Class currentClass = new Object() {
      }.getClass().getEnclosingClass();
      ClassLoader classLoader = currentClass.getClassLoader();
      URL resource = classLoader.getResource(fileName);
      img = ImageIO.read(resource);
    } catch (IOException e) {
      System.err.println("Error reading '" + fileName + '"');
      throw new RuntimeException(e);
    }
    return img;
  }
}
