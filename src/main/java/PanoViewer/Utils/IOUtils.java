/*
 *
 */
package PanoViewer.Utils;

import java.io.InputStream;

/**
 *
 * @author kshan
 */
public class IOUtils {

  public static void chooseFile()
  {

  }

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
}
