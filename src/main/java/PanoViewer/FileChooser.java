package PanoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {

    private static FileChooser instance = new FileChooser();

    public static FileChooser getInstance() {
        return instance;
    }

    private FileChooser() {

    }

    public JFileChooser chooser() {
        JFileChooser fileChooser = new JFileChooser();
        String dir = System.getProperty("user.dir");
        FileFilter fileFilter = new FileNameExtensionFilter("Images Files", ImageIO.getReaderFileSuffixes());
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setFileFilter(fileFilter);
        File file = new File(dir);
        fileChooser.setCurrentDirectory(file);
        fileChooser.showOpenDialog(null);
        return fileChooser;
    }
}
