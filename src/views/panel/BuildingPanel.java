package views.panel;

import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import views.button.CityButton;
import views.view.StyledLabel;

public class BuildingPanel extends JPanel {
  private boolean built = false;
  private CityButton upgrade;
  private StyledLabel buildingName;
  private JTextArea info;
  private City city;

  public BuildingPanel(String name, City city) {
    this.city = city;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public JTextArea getInfo() {
    return info;
  }

  public void setInfo(JTextArea info) {
    this.info = info;
  }

  public CityButton getUpgrade() {
    return upgrade;
  }

  public void setUpgrade(CityButton upgrade) {
    this.upgrade = upgrade;
  }

  public StyledLabel getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(StyledLabel buildingName) {
    this.buildingName = buildingName;
  }

  public boolean isBuilt() {
    return built;
  }

  public void setBuilt(boolean built) {
    this.built = built;
  }

}
