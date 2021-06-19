package views;

import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import engine.City;

import java.awt.*;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.*;
import java.io.IOException;

public class MilitaryBuildingPanel extends BuildingPanel {
  private StyledButton recruit;

  public MilitaryBuildingPanel(ActionListener a, String name, City city) throws FontFormatException, IOException {
    super(a, name, city);
    setLayout(new GridLayout(2, 2));
    setUpgrade(new CityButton("Build", 30));
    getUpgrade().setEnabled(true);
    getUpgrade().addActionListener(a);
    getUpgrade().setCity(city);
    setInfo(new JTextArea("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX \n xxxxxxxxxxxxxxxxxx"));
    getInfo().setEditable(false);
    getInfo().setVisible(false);
    setRecruit(new StyledButton("Recruit", 30));
    recruit.setEnabled(false);
    add(new JLabel("<html><h1><strong><i>" + name + "</i></strong></h1><hr></html>"));
    add(getUpgrade());
    add(getInfo());
    add(recruit);
  }

  public StyledButton getRecruit() {
    return recruit;
  }

  public void setRecruit(StyledButton recruit) {
    this.recruit = recruit;
  }

}