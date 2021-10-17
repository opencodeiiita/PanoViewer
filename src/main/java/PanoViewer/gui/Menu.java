package PanoViewer.gui;

import PanoViewer.MainScreen;
import PanoViewer.Mode;
import PanoViewer.SwitchModes;
import PanoViewer.Utils.IOUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JMenuBar {

  private JMenu File;// creating menu objects
  private JMenu Help;// creating menu objects
  private JMenu options;// creating menu objects
  private JMenuItem open, exit , settings;// creating menuitem objects
  private JMenuItem About;// creating menuitem objects
  private JMenu mode;
  private JCheckBoxMenuItem flat;
  private JCheckBoxMenuItem panoramic;
  private ButtonGroup group = new ButtonGroup();

  private static Menu instance;// creating a menu instance
  // private constructor for implementing singleton design principle

  private Menu() {
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
    add(Help);
    add(options);
    add(mode);
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
      public void actionPerformed(ActionEvent e) {}

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
        SwitchModes.getInstance().setCurrentMode(Mode.Flat);
      }
    });

    panoramic.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwitchModes.getInstance().setCurrentMode(Mode.Panoramic);
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
}
