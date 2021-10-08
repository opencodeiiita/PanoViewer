package PanoViewer.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JMenuBar {
  private  JMenu File;//creating menu objects
  private JMenu  Help;//creating menu objects
  private JMenuItem Open, Exit;//creating menuitem objects
  private JMenuItem About;//creating menuitem objects
  private static Menu instance;//creating a menu instance
  //private constructor for implementing singleton design principle
  private Menu() {
//        menuBar=new JMenuBar();
    File = new JMenu("File");
    Help = new JMenu("Help");
    Open = new JMenuItem("Open");
    Exit = new JMenuItem("Exit");
    About = new JMenuItem("About");
    add(File);
    add(Help);
    File.add(Open);
    File.add(Exit);
    Help.add(About);
  }
  //getter method
  public static Menu getInstance()
  {
    if(instance==null)
    {
      instance=new Menu();
    }
    return instance;
  }
  //  shortcut to Open menu item (ctrl + F)
  Open.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
  
  // actionlistener to the Open menu item calling chooseFile() static method from IOUtils class
  Open.addActionListener(new ActionListener() 
   {
        IOUtils.chooseFile();    
   });
  
}
