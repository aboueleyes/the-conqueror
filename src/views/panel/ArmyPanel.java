package views.panel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
  
  public ArmyPanel (ActionListener a ,Army army) throws FontFormatException, IOException{
    this.army = army;
    setLayout(new BorderLayout());
    action1 = new ArmyButton("TargetCity", 20);
    action1.setArmy(army);
    action2 = new ArmyButton("SiegeCity", 20);
    action2.setArmy(army);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(action1);
    buttonPanel.add(action2);
    add(buttonPanel,BorderLayout.PAGE_END);
    info.setText("arg0");
    add(info);
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
