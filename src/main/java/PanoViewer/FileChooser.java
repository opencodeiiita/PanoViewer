package PanoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

    private JFileChooser fileChooser;
    private FileFilter fileFilter;
    private static FileChooser instance = new FileChooser();

    public static FileChooser getInstance() {
        return instance;
    }

    private FileChooser() {
        String dir = System.getProperty("user.dir");
        fileChooser = new JFileChooser(dir);
        fileFilter = new FileNameExtensionFilter("Images Files", ImageIO.getReaderFileSuffixes());
    }

    public JFileChooser chooser() {
        fileChooser.setFileFilter(fileFilter);
        return fileChooser;
    }
}
