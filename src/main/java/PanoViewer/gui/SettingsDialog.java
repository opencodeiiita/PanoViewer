package PanoViewer.gui;
import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JPanel {

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel themesPanel = new JPanel();
    private JLabel theme = new JLabel("Theme:");
    private JComboBox<String> comboBox = new JComboBox<String>();
    private JButton apply = new JButton("Apply");
    private JButton restore = new JButton("Restore");
    //private JButton ok = new JButton("OK");

    private static PanoViewer.gui.SettingsDialog instance;

    public SettingsDialog() {

        tabbedPane.add("General Look", themesPanel);
        this.setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.NORTH);

        themesPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.gridx= 4;
        gbc.gridy = 0;
        themesPanel.add(comboBox, gbc);
        comboBox.addItem("Default");
        comboBox.addItem("Browse...");


        gbc.insets = new Insets(2, 2, 0, 2);

        // gbc.anchor = GridBagConstraints.BASELINE;
        gbc.gridwidth =1;
        gbc.gridx=1;
        gbc.gridy=4;
        themesPanel.add(restore,gbc);

        gbc.gridwidth = 3;
        gbc.gridx=3;
        gbc.gridy=4;
        themesPanel.add(apply, gbc);

        gbc.anchor = GridBagConstraints.WEST;
       // gbc.gridwidth =4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        themesPanel.add(theme, gbc);



        //add(new JButton("Apply" ), BorderLayout.SOUTH);

        add(tabbedPane);
        this.setSize(400, 500);
        this.setVisible(true);
    }

    public static PanoViewer.gui.SettingsDialog getInstance() {
        if (instance == null) {
            instance = new SettingsDialog(); //settings
        }
//    instance.setVisible(true);
        return instance;
    }
}

//    public static void main(String[] args){
//
//        SettingsDialog tp = new SettingsDialog();
//        tp.setSize(600,400);
//        tp.setVisible(true);
//        JFrame frame = new JFrame("Settings");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(320,420);
//        frame.setVisible(true);
//
//        frame.add(tp);
//    }
//}
