package views.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

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

  public UnitButton getInitiate() {
    return initiate;
  }

  public void setInitiate(UnitButton initiate) {
    this.initiate = initiate;
  }

  public UnitButton getRelocate() {
    return relocate;
  }

  public void setRelocate(UnitButton relocate) {
    this.relocate = relocate;
  }

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public DefendingUnitPanel(ActionListener a, Unit unit) {
    this.unit = unit;
    setLayout(new BorderLayout());
    ImagePanel background = new ImagePanel(new ImageIcon(setUnitPanelTypeImage(unit)).getImage());

    background.setLayout(new BorderLayout());
    initiate = new UnitButton("Initiate Army", 15);
    initiate.setUnit(unit);
    relocate = new UnitButton("Relocate", 15);
    relocate.setUnit(unit);
    setLayout(new BorderLayout());
    JPanel container = new JPanel();

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(2, 1));
    buttonPanel.add(initiate);
    buttonPanel.add(relocate);
    container.setLayout(new BorderLayout());
    container.add(buttonPanel, BorderLayout.EAST);
    container.setOpaque(false);
    background.add(info, BorderLayout.EAST);
    background.add(container, BorderLayout.PAGE_END);
    info.setText(unit.toString());
    info.setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 16));
    info.setOpaque(false);
    info.setEditable(false);
    buttonPanel.setOpaque(false);
    initiate.addActionListener(a);
    relocate.addActionListener(a);
    background.setOpaque(false);
    add(background);
  }

  private String setUnitPanelTypeImage(Unit unit) {
    if (unit instanceof Archer) {
      return "./assets/img/archer.png";
    } else if (unit instanceof Cavalry) {
      return "./assets/img/cavalry.png";
    } else {
      return "./assets/img/infantry.png";
    }

  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public void enableRelocate() {
    relocate.setEnabled(true);
  }

  public void disableRelocate() {
    relocate.setEnabled(false);
  }
}
