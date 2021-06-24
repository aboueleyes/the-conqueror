package views.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import engine.City;
import units.Army;
import views.button.ArmyButton;

public class ArmyPanel extends JPanel {
  private Army army;
  private JTextArea info = new JTextArea();
  private ArmyButton action1;
  private ArmyButton action2;
  private ArmyButton startBattle;
  private City city;
  private JComboBox<String> cities = new JComboBox<>(citiesNames);

  public JComboBox<String> getCities() {
    return cities;
  }

  public void setCities(JComboBox<String> cities) {
    this.cities = cities;
  }

  private static final String[] citiesNames = { "Cairo", "Rome", "Sparta" };

  public ArmyPanel(ActionListener a, Army army) {
    this.army = army;

    ImagePanel background = new ImagePanel(new ImageIcon("src/images/army.png").getImage());
    setLayout(new BorderLayout());
    background.setLayout(new BorderLayout());
    action1 = new ArmyButton("TargetCity", 20);
    action1.setArmy(army);
    action2 = new ArmyButton("SiegeCity", 20);
    action2.setArmy(army);
    startBattle = new ArmyButton("Start Battle", 20);
    startBattle.setArmy(army);
    JPanel buttonPanel = new JPanel();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    panel1.add(cities, BorderLayout.NORTH);

    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.add(action1);
    buttonPanel.add(action2);
    buttonPanel.add(startBattle);
    panel1.add(buttonPanel, BorderLayout.SOUTH);
    background.add(panel1, BorderLayout.PAGE_END);
    info.setText("");
    background.add(info,BorderLayout.CENTER);
    action1.addActionListener(a);
    action2.addActionListener(a);
    startBattle.addActionListener(a);
    info.setOpaque(false);
    add(background);
    setOpaque(false);
  }

  public ArmyButton getAction3() {
    return startBattle;
  }

  public void setAction3(ArmyButton action3) {
    this.startBattle = action3;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public ArmyButton getAction2() {
    return action2;
  }

  public void setAction2(ArmyButton action2) {
    this.action2 = action2;
  }

  public Army getArmy() {
    return army;
  }

  public ArmyButton getAction1() {
    return action1;
  }

  public void setAction(ArmyButton action) {
    this.action1 = action;
  }

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public void setArmy(Army army) {
    this.army = army;
  }

}
