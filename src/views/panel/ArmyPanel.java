package views.panel;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import units.Army;
import views.button.ArmyButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.FontFormatException;
public class ArmyPanel extends JPanel{
  private Army army;
  private JTextArea info = new JTextArea();
  private ArmyButton action1;
  private ArmyButton action2;
  private City city;
  private JComboBox cities = new JComboBox<>(citiesNames);
  private static final String [] citiesNames = {"Cairo","Rome","Sparta"};
  
  public ArmyPanel (ActionListener a ,Army army) throws FontFormatException, IOException{
    this.army = army;
    setLayout(new BorderLayout());
    action1 = new ArmyButton("TargetCity", 20);
    action1.setArmy(army);
    action2 = new ArmyButton("SiegeCity", 20);
    action2.setArmy(army);
    JPanel buttonPanel = new JPanel();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    panel1.add(cities,BorderLayout.NORTH);

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(action1);
    buttonPanel.add(action2);
    panel1.add(buttonPanel,BorderLayout.SOUTH);
    add(panel1,BorderLayout.PAGE_END);
    info.setText("arg0");
    add(info);
    action1.addActionListener(a);
    action2.addActionListener(a);
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
