package views.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import units.Archer;
import units.Cavalry;
import units.Unit;
import views.button.UnitButton;

public class UnitPanel extends JPanel {
  Unit unit;
  private JTextArea info = new JTextArea();
  private UnitButton action1;

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
    ImagePanel background = new ImagePanel(new ImageIcon(setUnitPanelTypeImage(unit)).getImage());
    setLayout(new BorderLayout());
     background.setLayout(new BorderLayout());
    action1 = new UnitButton("Select", 20);
    action1.setUnit(unit);
    
    JPanel buttonPanel = new JPanel();

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(action1);
  

    background.add(buttonPanel, BorderLayout.PAGE_END);
    info.setText(unit.toString());
    info.setOpaque(false);
    info.setEditable(false);
    background.add(info);
    action1.addActionListener(a);
    add(background);
  }

  private String setUnitPanelTypeImage(Unit unit) {
    if (unit instanceof Archer) {
      return "src/images/archer.png";
    } else if (unit instanceof Cavalry) {
      return "src/images/cavalry.png";
    } else {
      return "src/images/infantry.png";
    }

  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

}
