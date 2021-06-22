package views.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
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

  public void setAction1(UnitButton initiate) {
    this.initiate = initiate;
  }

  public DefendingUnitPanel(ActionListener a, Unit unit) {
    this.unit = unit;
    setLayout(new BorderLayout());
    // ImagePanel background = setUnitPanelTypeImage(unit);
    ImagePanel background = new ImagePanel(new ImageIcon(setUnitPanelTypeImage(unit)).getImage());
    add(background);
    background.setLayout(new BorderLayout());
    initiate = new UnitButton("Initiate Army", 20);
    initiate.setUnit(unit);
    relocate = new UnitButton("Relocate", 20);
    relocate.setUnit(unit);
    setLayout(new BorderLayout());

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
    info.setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 16));
    // info.setForeground(Color.LIGHT_GRAY);
    info.setOpaque(false);

    initiate.addActionListener(a);
    relocate.addActionListener(a);

  }

  private String setUnitPanelTypeImage(Unit unit) {
    ImagePanel background;
    if (unit instanceof Archer) {
      return "src/images/archer.jpg";
    } else if (unit instanceof Cavalry) {
      return "src/images/cavalry.jpg";
    } else {
      return "src/images/infantry.jpg";
    }

  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

}
