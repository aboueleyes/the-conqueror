package views.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import engine.City;
import units.Army;
import units.Unit;
import views.panel.BuildingPanel;
import views.panel.CardsPanel;
import views.panel.DefendingUnitPanel;
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
  private Army selected;

  public JPanel getBuildingsPanel() {
    return buildingsPanel;
  }

  public Army getSelected() {
    return selected;
  }

  public void setSelected(Army selected) {
    this.selected = selected;
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

  public CityView(ActionListener a, PlayerPanel playerPanel, City city) {
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

  public void addArmyPane() {
    armyPanel.setLayout(new GridLayout(1, 3));
    armyPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
    unitsCards = new CardsPanel();
    armyCards = new CardsPanel();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());

    JLabel label1 = new JLabel("<html><h1><strong><i>" + "Defending Army" + "</i></strong></h1><hr></html>");

    label1.setBorder(new EmptyBorder(5, 5, 5, 5));
    panel1.add(label1, BorderLayout.PAGE_START);
    panel1.add(unitsCards);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new BorderLayout());
    JLabel label2 = new JLabel("<html><h1><strong><i>" + "Stationary Armies" + "</i></strong></h1><hr></html>");
    label2.setBorder(new EmptyBorder(5, 5, 5, 5));
    panel2.add(label2, BorderLayout.PAGE_START);
    panel2.add(armyCards);
    armyPanel.add(panel1, BorderLayout.WEST);
    armyPanel.add(new JLabel());
    armyPanel.add(panel2, BorderLayout.EAST);

  }

  public void addBuildingsPanel(ActionListener a) {
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

  public void addDefendingPanel(Unit unit, ActionListener a) {
    unitsCards.addDefendingPanel(unit, a);
    if (selected == null) {
      unitsCards.getDefendingUnitPanel().disableRelocate();
    }
  }

  private void setBuildingPanels(ActionListener a) {
    buildlingsSlavePanels[0] = new EconomicalBuildingPanel(a, BUILDING_NAMES[0], city);
    buildlingsSlavePanels[1] = new EconomicalBuildingPanel(a, BUILDING_NAMES[1], city);
    buildlingsSlavePanels[2] = new MilitaryBuildingPanel(a, BUILDING_NAMES[2], city);
    buildlingsSlavePanels[3] = new MilitaryBuildingPanel(a, BUILDING_NAMES[3], city);
    buildlingsSlavePanels[4] = new MilitaryBuildingPanel(a, BUILDING_NAMES[4], city);
  }

}
