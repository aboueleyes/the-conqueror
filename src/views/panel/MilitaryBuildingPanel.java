package views.panel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import engine.City;
import views.button.CityButton;
import java.awt.Font;

public class MilitaryBuildingPanel extends BuildingPanel {
  private CityButton recruit;

  public MilitaryBuildingPanel(ActionListener a, String name, City city) {
    super(a, name, city);
    setLayout(new BorderLayout());
    ImagePanel background = new ImagePanel(new ImageIcon(setImageType(name)).getImage());
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

    setRecruit(new CityButton("Recruit", 15));
    recruit.setEnabled(false);
    JLabel label = new JLabel("<html><h2><strong><i>" + name + "</i></strong></h2><hr></html>");
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(2, 1));
    buttonPanel.add(getUpgrade());
    buttonPanel.add(recruit);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(getInfo());
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    panel1.add(buttonPanel, BorderLayout.EAST);
    panel.add(panel1);
    background.add(label, BorderLayout.PAGE_START);
    background.add(panel, BorderLayout.PAGE_END);
    // add(recruit);
    recruit.setCity(city);
    getRecruit().addActionListener(a);
    add(background);
    getInfo().setVisible(false);
  }

  public String setImageType(String type) {
    if (type.equals("ArcheryRange")) {
      return "./assets/img/buildings/archeryRange.png";
    } else if (type.equals("Barracks")) {
      return "./assets/img/buildings/barracks.png";
    } else {
      return "./assets/img/buildings/stable.png";
    }
  }

  public CityButton getRecruit() {
    return recruit;
  }

  public void setRecruit(CityButton recruit) {
    this.recruit = recruit;
  }

  public void setRecruitEnabled() {
    this.recruit.setEnabled(true);
  }

}