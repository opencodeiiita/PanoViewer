package PanoViewer.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static PanoViewer.Utils.IOUtils.getFileFromResourceAsStream;
import static PanoViewer.Utils.imageutils.open;
import static PanoViewer.Utils.imageutils.scaleImage;

/*
@author-Bipul Kumar
Constructor to initialise the object of AboutDialog.
This creates a basic UI.
This follows Singleton Pattern.
 */
public class AboutDialog extends JPanel {
  private String mess1, mess2, mess3;
  private URI url;
  private JLabel lab;
  private JButton but;
  private static AboutDialog instance;

  private AboutDialog(JFrame owner) {
    setLayout(new GridBagLayout());
    String filePath = "Images/99anjali.PNG";
    InputStream image = getFileFromResourceAsStream(filePath);
    ImageIcon icon = null;
    try {
      assert image != null;
      icon = new ImageIcon(ImageIO.read(image));
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      String repoLink = "https://github.com/opencodeiiita/PanoViewer";
      url = new URI(repoLink);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    GridBagConstraints gbc = new GridBagConstraints();
    assert icon != null;
    icon = scaleImage(icon, icon.getIconWidth() / 3, icon.getIconHeight() / 3);
    lab = new JLabel("<html>PanoViewer-OpenGl based Image Viewer for 360<br>degree Panoramic Images<br><br>Version 1.0<br><br>Minimum requirements for the App to run JDK 8.0+ <br> and OpenGl 4.0+</html>", icon, JLabel.CENTER);
    gbc.gridy = 0;
    gbc.gridx = 0;
    add(lab, gbc);
    lab.setVerticalTextPosition(JLabel.TOP);
    lab.setHorizontalAlignment(JLabel.CENTER);
    but = new JButton("Source Code");
    but.addActionListener(actionEvent -> open(url));
    gbc.gridy = 1;
    gbc.gridx = 0;
    add(but, gbc);
    setBackground(Color.LIGHT_GRAY);
  }





  /*
  Getter Method
   */
  private static AboutDialog getInstance(JFrame owner) {
    if (instance == null) {
      instance = new AboutDialog(owner);
    }
    return instance;
  }

  //driver function
  public static void main(String[] args) {
    JFrame owner = new JFrame("Test");
    owner.setVisible(true);
    owner.setSize(200, 200);
    owner.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    instance = getInstance(owner);
    owner.add(instance);
    instance.setVisible(true);
  }
}
