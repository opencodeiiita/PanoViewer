/*
 * 
 */
package PanoViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author kshan
 */
public class MainScreen extends JPanel {

  JButton openButton;
  final JFileChooser fc;

  public MainScreen() {
    super();
    fc = new JFileChooser();
    setupOpenButton();
    this.add(openButton);
  }

  private void setupOpenButton() {
    openButton = new JButton("Open");
    openButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int showOpenDialog = fc.showOpenDialog(fc);
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
          openFile(fc.getSelectedFile());
        }
      }
    });
  }

  private void openFile(File selectedFile) {
    try {
      BufferedImage image = ImageIO.read(selectedFile);
      System.out.println(image.toString());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
