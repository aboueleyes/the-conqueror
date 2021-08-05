package views.panel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import views.button.CityButton;

public class EconomicalBuildingPanel extends BuildingPanel {

  public EconomicalBuildingPanel(ActionListener a, String name, City city) {
    super(a, name, city);
    setLayout(new BorderLayout());
    var background = new ImagePanel(new ImageIcon(setImageType(name)).getImage());
    background.setLayout(new BorderLayout());
    setUpgrade(new CityButton("Build", 15));
    getUpgrade().setEnabled(true);
    getUpgrade().addActionListener(a);
    getUpgrade().setCity(city);
    setInfo(new JTextArea(""));
    getInfo().setEditable(false);
    getInfo().setVisible(false);
    getInfo().setOpaque(false);
    getInfo().setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 16));

    var label = new JLabel("<html><h2><strong><i>" + name + "</i></strong></h2><hr></html>");
    var buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 1));
    buttonPanel.add(getUpgrade());

    var panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(getInfo());
    var panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    panel1.add(buttonPanel, BorderLayout.EAST);

    panel.add(panel1);
    background.add(label, BorderLayout.PAGE_START);
    background.add(panel, BorderLayout.PAGE_END);
    add(background);
  }

  public String setImageType(String type) {
    if (type.equals("Farm")) {
      return "./assets/img/buildings/farm.png";
    } else {
      return "./assets/img/buildings/market.png";
    }
  }

}
