package PanoViewer.gui;
import PanoViewer.LookFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    for (UIManager.LookAndFeelInfo info : LookFeel.getInstance().getAllLookAndFeel()) {
      if (info.getName().equals("Metal")) {
          comboBox.addItem("Default");
      }else {
          comboBox.addItem(info.getName());
      }
    }

    restore.addActionListener(new ActionListener() {
    @Override
      public void actionPerformed(ActionEvent e) {
        LookFeel.getInstance().setCurrentLook(LookFeel.getInstance().getAllLookAndFeel()[0]);
        comboBox.setSelectedItem("Default");
      }
    });

    apply.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i = comboBox.getSelectedIndex();
        LookFeel.getInstance().setCurrentLook(LookFeel.getInstance().getAllLookAndFeel()[i]);
      }
    });

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
    return instance;
  }
}
