package views.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import units.Archer;
import units.Cavalry;
import units.Unit;

public class InfoUnitPanel extends JPanel {
  Unit unit;
  private JTextArea info = new JTextArea();

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public InfoUnitPanel(ActionListener a, Unit unit) {
    this.unit = unit;
    setLayout(new BorderLayout());
    ImagePanel background = new ImagePanel(new ImageIcon(setUnitPanelTypeImage(unit)).getImage());

    background.setLayout(new BorderLayout());

    setLayout(new BorderLayout());

    background.add(info, BorderLayout.EAST);

    info.setText(unit.toString());
    info.setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 16));
    info.setOpaque(false);
    info.setEditable(false);
    background.setOpaque(false);
    add(background);
    setOpaque(false);
  }

  private String setUnitPanelTypeImage(Unit unit) {
    if (unit instanceof Archer) {
      return "./assets/img/units/archer.png";
    } else if (unit instanceof Cavalry) {
      return "./assets/img/units/cavalry.png";
    } else {
      return "./assets/img/units/infantry.png";
    }

  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

}
