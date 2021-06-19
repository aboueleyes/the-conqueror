package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

import engine.City;

public class CityView extends JFrame {
  private PlayerPanel playerPanel;
  private JPanel buildingsPanel = new JPanel();
  private JPanel armyPanel = new JPanel();
  private City city;
  private BuildingPanel[] buildlingsSlavePanels = new BuildingPanel[5];
  public static final String[] BUILDING_NAMES = { "Market", "Farm", "Barracks", "Stable", "ArcheryRange" };

  public JPanel getBuildingsPanel() {
    return buildingsPanel;
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
    armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.Y_AXIS));
    armyPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
    armyPanel.add(new StyledLabel("Controlled Armies", 25, true));
    armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.X_AXIS));
  }

  public void addBuildingsPanel(ActionListener a) throws FontFormatException, IOException {
    GridLayout gridLayout = new GridLayout(0, 5);
    gridLayout.setHgap(10);
    setBackground(Color.decode("#C8AE81"));
    buildingsPanel.setLayout(gridLayout);
    setBuildingPanels(a);

    setActionBuildingsButtons();

    for (BuildingPanel jPanel : buildlingsSlavePanels) {
      buildingsPanel.add(jPanel);
    }
  }

  private void setActionBuildingsButtons() {
    System.out.println("PLZ RUN");
    buildlingsSlavePanels[0].getUpgrade().setActionCommand(BUILDING_NAMES[0]);
    System.out.println(buildlingsSlavePanels[0].getUpgrade().getActionCommand());
    buildlingsSlavePanels[1].getUpgrade().setActionCommand(BUILDING_NAMES[1]);
    buildlingsSlavePanels[2].getUpgrade().setActionCommand(BUILDING_NAMES[2]);
    buildlingsSlavePanels[3].getUpgrade().setActionCommand(BUILDING_NAMES[3]);
    buildlingsSlavePanels[4].getUpgrade().setActionCommand(BUILDING_NAMES[4]);
  }

  private void setBuildingPanels(ActionListener a) throws FontFormatException, IOException {
    buildlingsSlavePanels[0] = new EconomicalBuildingPanel(a, BUILDING_NAMES[0], city);
    buildlingsSlavePanels[1] = new EconomicalBuildingPanel(a, BUILDING_NAMES[1], city);
    buildlingsSlavePanels[2] = new MilitaryBuildingPanel(a, BUILDING_NAMES[2], city);
    buildlingsSlavePanels[3] = new MilitaryBuildingPanel(a, BUILDING_NAMES[3], city);
    buildlingsSlavePanels[4] = new MilitaryBuildingPanel(a, BUILDING_NAMES[4], city);
  }

}
