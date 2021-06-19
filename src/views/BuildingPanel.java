package views;

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

public class BuildingPanel extends JPanel {
  private boolean built = false;
  private CityButton upgrade;
  private StyledLabel buildingName;
  private JTextArea info;
  private City city;

  public BuildingPanel(ActionListener a, String name, City city) throws FontFormatException, IOException {
    this.city = city;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public CityButton getUpgrade() {
    return upgrade;
  }

  public void setUpgrade(CityButton upgrade) {
    this.upgrade = upgrade;
  }

  public StyledLabel getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(StyledLabel buildingName) {
    this.buildingName = buildingName;
  }

  public boolean isBuilt() {
    return built;
  }

  public void setBuilt(boolean built) {
    this.built = built;
  }

}