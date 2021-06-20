package views.panel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.io.IOException;

import units.Unit;
import views.button.ArmyButton;
import views.button.UnitButton;

import java.awt.*;

public class UnitPanel extends JPanel {
  Unit unit;
  private JTextArea info = new JTextArea();
  private UnitButton action1;
  private UnitButton action2;

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public UnitButton getAction1() {
    return action1;
  }

  public void setAction1(UnitButton action1) {
    this.action1 = action1;
  }

  public UnitPanel(ActionListener a, Unit unit) {
    this.unit = unit;
    setLayout(new BorderLayout());
    action1 = new UnitButton("Initiate Army", 20);
    action1.setUnit(unit);
    action2 = new UnitButton("Relocate", 20);
    action2.setUnit(unit);
    JPanel buttonPanel = new JPanel();

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(action1);
    buttonPanel.add(action2);

    add(buttonPanel, BorderLayout.PAGE_END);
    info.setText(unit.toString());
    add(info);
    action1.addActionListener(a);
    action2.addActionListener(a);
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }
}
