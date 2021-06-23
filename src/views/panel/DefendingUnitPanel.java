package views.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import java.awt.GridLayout;
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
    ImagePanel background = new ImagePanel(new ImageIcon(setUnitPanelTypeImage(unit)).getImage());
    
    background.setLayout(new BorderLayout());
    initiate = new UnitButton("Initiate Army", 20);
    initiate.setUnit(unit);
    relocate = new UnitButton("Relocate", 20);
    relocate.setUnit(unit);
    setLayout(new BorderLayout());

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.add(initiate);
    buttonPanel.add(new JLabel());
    buttonPanel.add(relocate);
   
    background.add(info,BorderLayout.EAST);
    background.add(buttonPanel,BorderLayout.PAGE_END);
    info.setText(unit.toString());
    info.setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 16));
    info.setOpaque(false);
    buttonPanel.setOpaque(false);
    initiate.addActionListener(a);
    relocate.addActionListener(a);
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
