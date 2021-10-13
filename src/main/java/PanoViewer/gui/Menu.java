package PanoViewer.gui;

import PanoViewer.ImagePanel;
import PanoViewer.SwitchModes;
import PanoViewer.Utils.IOUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JMenuBar {

  private JMenu File;// creating menu objects
  private JMenu Help;// creating menu objects
  private JMenuItem Open, Exit;// creating menuitem objects
  private JMenuItem About;// creating menuitem objects
  private JMenu Mode;
  private JMenuItem Flat;
  private JMenuItem Panoramic;

  private static Menu instance;// creating a menu instance
  // private constructor for implementing singleton design principle

  private Menu() {
    // menuBar=new JMenuBar();
    File = new JMenu("File");
    Help = new JMenu("Help");
    Open = new JMenuItem("Open");
    Exit = new JMenuItem("Exit");
    About = new JMenuItem("About");
    Mode = new JMenu("Mode");
    Flat = new JMenuItem("Flat");
    Panoramic = new JMenuItem("Panoramic");
    add(File);
    add(Help);
    add(Mode);
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
    Mode.add(Flat);
    Mode.add(Panoramic);

    Flat.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwitchModes.getInstance().switchingModes(ImagePanel.FlatImages);
      }
    });

    Panoramic.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwitchModes.getInstance().switchingModes(ImagePanel.PanoramicImages);
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
