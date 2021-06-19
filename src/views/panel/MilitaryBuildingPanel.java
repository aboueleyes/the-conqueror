package views.panel;

import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import engine.City;
import views.button.CityButton;
import views.button.StyledButton;

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

  public void setRecruitEnabled() {
    this.recruit.setEnabled(true);
  }

}