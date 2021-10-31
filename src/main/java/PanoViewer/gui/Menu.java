package PanoViewer.gui;

import PanoViewer.MainScreen;
import PanoViewer.Mode;
import PanoViewer.ModeRecorder;
import PanoViewer.Utils.IOUtils;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class Menu extends JMenuBar implements PropertyChangeListener {

  private JMenu File;// creating menu objects
  private JMenu Help;// creating menu objects
  private JMenu options;// creating menu objects
  private JMenuItem open, exit , settings;// creating menuitem objects
  private JMenuItem About;// creating menu objects
  private JMenu mode;
  private JCheckBoxMenuItem flat;
  private JCheckBoxMenuItem panoramic;
  private ButtonGroup group = new ButtonGroup();
  private static Menu instance;// creating a menu instance

  // private constructor for implementing singleton design principle
  private Menu() {
    ModeRecorder.getInstance().addPropertyChangeListener(this);
    // menuBar=new JMenuBar();
    File = new JMenu("File");
    Help = new JMenu("Help");
    options = new JMenu("Options");
    open = new JMenuItem("Open");
    exit = new JMenuItem("Exit");
    settings = new JMenuItem("Settings");
    About = new JMenuItem("About");
    mode = new JMenu("Mode");
    flat = new JCheckBoxMenuItem("Flat");
    panoramic = new JCheckBoxMenuItem("Panoramic");
    panoramic.setSelected(true);
    add(File);
    add(mode);
    //add(Help);
    add(options);
    add(Help);
    group.add(flat);
    group.add(panoramic);

    // shortcut to Open menu item (ctrl + F)
    open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

    // actionlistener to the Open menu item calling chooseFile() static method from
    // IOUtils class
    open.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        IOUtils.getFile();
      }

    });

    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }

    });


    settings.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog jd=new JDialog(MainScreen.getInstance(),true);
        jd.add(SettingsDialog.getInstance());
        //jd.add(SettingsDialog.getInstance());
        jd.setTitle("SettingsDialog");
        //jd.setSize(450,420);
        jd.pack();
        jd.setLocationRelativeTo(MainScreen.getInstance());
        jd.setVisible(true);
      }

    });

    File.add(open);
    File.add(exit);
    Help.add(About);
    options.add(settings);
    mode.add(flat);
    mode.add(panoramic);
    flat.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ModeRecorder.getInstance().setCurrentMode(Mode.Flat);
      }
    });

    panoramic.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ModeRecorder.getInstance().setCurrentMode(Mode.Panoramic);
      }
    });
    About.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        JDialog jd=new JDialog(MainScreen.getInstance(),true);
        jd.add(AboutDialog.getInstance());
        jd.setTitle("About");
        jd.pack();
        jd.setLocationRelativeTo(MainScreen.getInstance());
        jd.setVisible(true);
      }
    });
  }

  // getter method
  public static Menu getInstance() {
    if (instance == null) {
      instance = new Menu();
    }
    return instance;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("mode")) {
      Mode newMode = (Mode) evt.getNewValue();
      if (newMode.equals(Mode.Flat)) {
        flat.setSelected(true);
      }else {
        panoramic.setSelected(true);
      }
    }
  }

}
