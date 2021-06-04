/*
 * 
 */
package PanoViewer.Utils;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kshan
 */
public class IOUtils {

  public static InputStream getFileFromResourceAsStream(String fileName) {
    Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    ClassLoader classLoader = currentClass.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);
    if (inputStream == null) {
      return null;
    } else {
      return inputStream;
    }
  }

  public static File getFileFromResource(String fileName) {
    File file = null;
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
}
