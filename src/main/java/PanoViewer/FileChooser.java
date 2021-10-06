package PanoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

    private static FileChooser instance = new FileChooser();

    public static FileChooser getInstance() {
        return instance;
    }

    private JFileChooser fileChooser;

    private FileChooser() {
        String dir = System.getProperty("user.dir");
        fileChooser = new JFileChooser(dir);
    }

    public JFileChooser chooser() {
        FileFilter fileFilter = new FileNameExtensionFilter("Images Files", ImageIO.getReaderFileSuffixes());
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setFileFilter(fileFilter);
        return fileChooser;
    }
}
