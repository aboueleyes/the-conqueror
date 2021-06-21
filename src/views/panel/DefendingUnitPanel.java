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

public class DefendingUnitPanel extends JPanel {
  Unit unit;
  private JTextArea info = new JTextArea();
  private UnitButton initiate;
  private UnitButton relocate;

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public UnitButton getAction1() {
    return initiate;
  }

  public void setAction1(UnitButton action1) {
    this.initiate = action1;
  }

  public DefendingUnitPanel(ActionListener a, Unit unit) {
    this.unit = unit;
    setLayout(new BorderLayout());
    ImagePanel background = setUnitPanelTypeImage(unit);

    // background.setLayout(new BorderLayout());
    initiate = new UnitButton("Initiate Army", 20);
    initiate.setUnit(unit);
    relocate = new UnitButton("Relocate", 20);
    relocate.setUnit(unit);
    JPanel buttonPanel = new JPanel();

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(initiate);
    buttonPanel.add(relocate);
    background.add(info, BorderLayout.CENTER);
    background.add(buttonPanel, BorderLayout.PAGE_END);
    info.setText(unit.toString());

    initiate.addActionListener(a);
    relocate.addActionListener(a);
    add(background);
  }

  private ImagePanel setUnitPanelTypeImage(Unit unit) {
    ImagePanel background;
    if (unit instanceof Archer) {
      background = new ImagePanel(new ImageIcon("src/images/archer.jpg").getImage());
    } else if (unit instanceof Cavalry) {
      background = new ImagePanel(new ImageIcon("src/images/cavalry.jpg").getImage());
    } else {
      background = new ImagePanel(new ImageIcon("src/images/infantry.jpg").getImage());
    }
    // background.setLayout(new BorderLayout());
    return background;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

}
