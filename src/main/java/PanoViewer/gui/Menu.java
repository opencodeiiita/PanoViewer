package PanoViewer.gui;

import PanoViewer.ImagePanel;
import PanoViewer.SwitchModes;
import PanoViewer.Utils.IOUtils;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JMenuBar {

  private JMenu File;// creating menu objects
  private JMenu Help;// creating menu objects
  private JMenuItem Open, Exit;// creating menuitem objects
  private JMenuItem About;// creating menuitem objects
  private JMenu mode;
  private JMenuItem flat;
  private JMenuItem panoramic;

  private static Menu instance;// creating a menu instance
  // private constructor for implementing singleton design principle

  private Menu() {
    // menuBar=new JMenuBar();
    File = new JMenu("File");
    Help = new JMenu("Help");
    Open = new JMenuItem("Open");
    Exit = new JMenuItem("Exit");
    About = new JMenuItem("About");
    mode = new JMenu("Mode");
    flat = new JMenuItem("Flat");
    panoramic = new JMenuItem("Panoramic");
    add(File);
    add(Help);
    add(mode);
    // shortcut to Open menu item (ctrl + F)
    Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

    // actionlistener to the Open menu item calling chooseFile() static method from
    // IOUtils class
    Open.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        IOUtils.getFile();
      }

    });

    Exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }

    });

    File.add(Open);
    File.add(Exit);
    Help.add(About);
    mode.add(flat);
    mode.add(panoramic);

    flat.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwitchModes.getInstance().setCurrentMode(ImagePanel.FlatImages);
      }
    });

    panoramic.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwitchModes.getInstance().setCurrentMode(ImagePanel.PanoramicImages);
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
