package PanoViewer.gui;

import javax.swing.*;

public class Menu extends JMenuBar {
   private JMenuBar menuBar;//making a menubar
    private  JMenu File;//creating menu objects
    private JMenu  Help;//creating menu objects
    private JMenuItem Open, Exit;//creating menuitem objects
    private JMenuItem About;//creating menuitem objects
    private static Menu instance;//creating a menu instance
    //private constructor for implementing singleton design principle
    private Menu() {
        menuBar=new JMenuBar();
        File = new JMenu("File");
        Help = new JMenu("Help");
        Open = new JMenuItem("Open");
        Exit = new JMenuItem("Exit");
        About = new JMenuItem("About");
        menuBar.add(File);
        menuBar.add(Help);
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

}
