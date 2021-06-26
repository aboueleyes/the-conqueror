package views.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import units.Army;
import views.button.ArmyButton;

public class StationaryArmyPanel extends JPanel {
  private Army army;
  private JTextArea info = new JTextArea();
  private ArmyButton selectArmy;

  public ArmyButton getSelectArmy() {
    return selectArmy;
  }

  public void setSelectArmy(ArmyButton selectArmy) {
    this.selectArmy = selectArmy;
  }

  private City city;

  public StationaryArmyPanel(ActionListener a, Army army) {
    this.army = army;
    ImagePanel background = new ImagePanel(new ImageIcon("./assets/img/army.png").getImage());
    setLayout(new BorderLayout());
    background.setLayout(new BorderLayout());
    selectArmy = new ArmyButton("Select", 15);
    selectArmy.setArmy(army);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(selectArmy);
    buttonPanel.setOpaque(false);
    background.add(buttonPanel, BorderLayout.PAGE_END);
    info.setText("");
    info.setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 16));
    background.add(info, BorderLayout.EAST);
    add(background);
    info.setOpaque(false);
    info.setEditable(false);
    selectArmy.addActionListener(a);
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Army getArmy() {
    return army;
  }

  public ArmyButton getAction1() {
    return selectArmy;
  }

  public void setAction(ArmyButton action) {
    this.selectArmy = action;
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
