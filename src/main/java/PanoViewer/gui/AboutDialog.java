package PanoViewer.gui;

import static PanoViewer.Utils.IOUtils.getFileFromResourceAsStream;
import static PanoViewer.Utils.imageutils.open;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * Constructor to initialise the object of AboutDialog. This creates a basic UI.
 * @author-Bipul Kumar
 */
public class AboutDialog extends JPanel {

  private String mess1, mess2, mess3;
  private URI url;
  private JLabel lab;
  private JButton but;
  private static AboutDialog instance;

 private AboutDialog() {
    setLayout(new GridBagLayout());
    String filePath = "Images/Logos/Panoviewer.png";
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
    // icon = scaleImage(icon, icon.getIconWidth()/2, icon.getIconHeight()/2);
    lab = new JLabel("<html> <br><br><br>&nbsp PanoViewer-OpenGl based Image Viewer for 360 &nbsp <br>&nbsp degree Panoramic Images &nbsp <br><br> &nbsp Version 1.0<br><br>&nbsp Minimum requirements for the App to run JDK 8.0+ &nbsp <br> &nbsp and OpenGl 4.0+</html>", icon, JLabel.CENTER);
    gbc.gridy = 0;
    gbc.gridx = 0;
    add(lab, gbc);
    lab.setVerticalTextPosition(JLabel.TOP);
    lab.setHorizontalAlignment(JLabel.CENTER);
    lab.setFont(new Font("Sans Serif", Font.PLAIN, 14));
    lab.setForeground(new Color(0xffffff));
    but = new JButton("Source Code");
    but.addActionListener(actionEvent -> open(url));
    gbc.gridy = 1;
    gbc.gridx = 0;
    add(but, gbc);

    setBackground(Color.black);
  }

  /*
  Getter Method
   */
  public static AboutDialog getInstance() {
    if (instance == null) {
      instance = new AboutDialog();
    }
//    instance.setVisible(true);
    return instance;
  }

  //driver function
//  public static void main(String[] args) {
//    JFrame owner = new JFrame("Test");
//    owner.setVisible(true);
//    owner.setSize(200, 200);
//    owner.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    instance = getInstance(owner);
//    owner.add(instance);
//    instance.setVisible(true);
//  }
}
