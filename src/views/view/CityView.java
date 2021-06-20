package views.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import buildings.MilitaryBuilding;
import engine.City;
import views.panel.BuildingPanel;
import views.panel.CardsPanel;
import views.panel.EconomicalBuildingPanel;
import views.panel.MilitaryBuildingPanel;
import views.panel.PlayerPanel;

public class CityView extends JFrame {
  private PlayerPanel playerPanel;
  private JPanel buildingsPanel = new JPanel();
  private JPanel armyPanel = new JPanel();
  private CardsPanel unitsCards;
  private CardsPanel armyCards;
  private City city;
  private BuildingPanel[] buildlingsSlavePanels = new BuildingPanel[5];
  public static final String[] BUILDING_NAMES = { "Market", "Farm", "Barracks", "Stable", "ArcheryRange" };

  public JPanel getBuildingsPanel() {
    return buildingsPanel;
  }

  public CardsPanel getArmyCards() {
    return armyCards;
  }

  public void setArmyCards(CardsPanel armyCards) {
    this.armyCards = armyCards;
  }

  public CardsPanel getUnitsCards() {
    return unitsCards;
  }

  public void setUnitsCards(CardsPanel unitsCards) {
    this.unitsCards = unitsCards;
  }

  public BuildingPanel[] getBuildlingsSlavePanels() {
    return buildlingsSlavePanels;
  }

  public void setBuildlingsSlavePanels(BuildingPanel[] buildlingsSlavePanels) {
    this.buildlingsSlavePanels = buildlingsSlavePanels;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public void setBuildingPanel(JPanel buildingPanel) {
    this.buildingsPanel = buildingPanel;
  }

  public PlayerPanel getPlayerPanel() {
    return playerPanel;
  }

  public void setPlayerPanel(PlayerPanel playerPanel) {
    this.playerPanel = playerPanel;
  }

  public CityView(ActionListener a, PlayerPanel playerPanel, City city) throws FontFormatException, IOException {
    this.setCity(city);
    setLayout(new BorderLayout());
    this.playerPanel = playerPanel;
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("The Conqueror");
    pack();
    getContentPane().add(playerPanel, BorderLayout.PAGE_START);
    getContentPane().add(buildingsPanel, BorderLayout.CENTER);
    addArmyPane();
    getContentPane().add(armyPanel, BorderLayout.SOUTH);
    addBuildingsPanel(a);
  }

  public void addArmyPane() throws FontFormatException, IOException {
    armyPanel.setLayout(new BorderLayout());
    armyPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
    unitsCards = new CardsPanel();
    armyCards = new CardsPanel();
    armyPanel.add(unitsCards, BorderLayout.WEST);
    armyPanel.add(armyCards, BorderLayout.EAST);

  }

  public void addBuildingsPanel(ActionListener a) throws FontFormatException, IOException {
    GridLayout gridLayout = new GridLayout(0, 5);
    gridLayout.setHgap(10);
    setBackground(Color.decode("#C8AE81"));
    buildingsPanel.setLayout(gridLayout);
    setBuildingPanels(a);
    setActionBuildingsButtons();
    setActionBuildingsRecruitButtons();
    for (BuildingPanel jPanel : buildlingsSlavePanels) {
      buildingsPanel.add(jPanel);
    }
  }

  private void setActionBuildingsButtons() {
    for (int i = 0; i < BUILDING_NAMES.length; i++) {
      buildlingsSlavePanels[i].getUpgrade().setActionCommand(BUILDING_NAMES[i]);
    }
  }

  private void setActionBuildingsRecruitButtons() {
    for (int i = 2; i < BUILDING_NAMES.length; i++) {
      MilitaryBuildingPanel panel = (MilitaryBuildingPanel) buildlingsSlavePanels[i];
      panel.getRecruit().setActionCommand("r" + BUILDING_NAMES[i]);
    }
  }

  private void setBuildingPanels(ActionListener a) throws FontFormatException, IOException {
    buildlingsSlavePanels[0] = new EconomicalBuildingPanel(a, BUILDING_NAMES[0], city);
    buildlingsSlavePanels[1] = new EconomicalBuildingPanel(a, BUILDING_NAMES[1], city);
    buildlingsSlavePanels[2] = new MilitaryBuildingPanel(a, BUILDING_NAMES[2], city);
    buildlingsSlavePanels[3] = new MilitaryBuildingPanel(a, BUILDING_NAMES[3], city);
    buildlingsSlavePanels[4] = new MilitaryBuildingPanel(a, BUILDING_NAMES[4], city);
  }

}
