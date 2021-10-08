package PanoViewer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutDialog extends JPanel {
  private String mess1, mess2, mess3;
  private URI url;
  private JLabel lab;
  private JButton but;
  private static AboutDialog instance;

  private AboutDialog(JFrame owner) throws URISyntaxException {
//    super(owner)
//    super(new GridBagLayout());
//    super(new GridLayout(1,2));
    setLayout(new GridBagLayout());
    mess1 = "PanoViewer-Converts any image to panorama";
    mess2 = "Version 1.0";
    mess3 = "Minimum requirements for the App to run JDK 8.0+ and OpenGl 4.0+";
    String title = mess1 + " " + mess2 + " " + mess3;
    ImageIcon icon = new ImageIcon("/home/crypto/projects/Comp/PanoViewer/src/main/resources/Images/Panoview.png");
    url = new URI("https://github.com/opencodeiiita/PanoViewer");
    GridBagConstraints gbc = new GridBagConstraints();
    lab = new JLabel(title, icon, JLabel.CENTER);
    gbc.gridy = 0;
    gbc.gridx = 0;
    add(lab, gbc);
/*
    lab=new JLabel(title,SwingConstants.TRAILING);
    owner.layout.add(icon, SwingConstants.LEADING);
*/
    lab.setVerticalTextPosition(JLabel.BOTTOM);
    lab.setHorizontalAlignment(JLabel.CENTER);
    but = new JButton("Repo Link");
    but.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        open(url);
      }
    });
    gbc.gridy = 0;
    gbc.gridx = 1;
    add(but, gbc);
  }

  private static void open(URI url) {
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();
      try {
        desktop.browse(url);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to launch the link, " + "your computer is likely misconfigured ." + "Cannot Open Link " + JOptionPane.WARNING_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null,
              "Java is not able to launch links on your computer.",
              "Cannot Launch Link", JOptionPane.WARNING_MESSAGE);

    }
  }

  private static AboutDialog getInstance(JFrame owner) throws URISyntaxException {
    if (instance == null) {
      instance = new AboutDialog(owner);
    }
    return instance;
  }
}
