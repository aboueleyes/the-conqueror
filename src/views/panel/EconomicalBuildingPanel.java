package views.panel;

import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import engine.City;
import views.button.CityButton;

public class EconomicalBuildingPanel extends BuildingPanel {

  public EconomicalBuildingPanel(ActionListener a, String name, City city) {
    super(a, name, city);
    setLayout(new GridLayout(2, 2));
    setUpgrade(new CityButton("Build", 30));
    getUpgrade().setEnabled(true);
    getUpgrade().addActionListener(a);
    getUpgrade().setCity(city);
    setInfo(new JTextArea(""));
    getInfo().setEditable(false);
    getInfo().setVisible(false);
    add(new JLabel("<html><h1><strong><i>" + name + "</i></strong></h1><hr></html>"));
    add(new JLabel(""));
    add(getUpgrade());
    add(getInfo());

  }

}
