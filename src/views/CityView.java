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

public class CityView extends JFrame {
  private PlayerPanel playerPanel;
  private JPanel buildingsPanel = new JPanel();
  private JPanel armyPanel = new JPanel();

  public JPanel getBuildingPanel() {
    return buildingsPanel;
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

  public CityView(ActionListener a, PlayerPanel playerPanel) throws FontFormatException, IOException {
    setLayout(new BorderLayout());
    this.playerPanel = playerPanel;
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("The Conqueror");
    pack();
    getContentPane().add(playerPanel, BorderLayout.PAGE_START);
    getContentPane().add(buildingsPanel, BorderLayout.CENTER);
    addBuildingsPanel(a);
    addArmyPane();
    getContentPane().add(armyPanel, BorderLayout.SOUTH);
  }

  public void addArmyPane() throws FontFormatException, IOException {
    armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.Y_AXIS));
    armyPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
    armyPanel.add(new StyledLabel("Controled Armies", 25, true));
    armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.X_AXIS));
  }

  public void addBuildingsPanel(ActionListener a) throws FontFormatException, IOException {
    GridLayout gridLayout = new GridLayout(0, 5);
    gridLayout.setHgap(10);
    setBackground(Color.decode("#C8AE81"));
    buildingsPanel.setLayout(gridLayout);
    buildingsPanel.add(new BuildingPanel(a, "Market"));
    buildingsPanel.add(new BuildingPanel(a, "Barracks"));
    buildingsPanel.add(new EconomicalBuildingPanel(a, "Farm"));
    buildingsPanel.add(new BuildingPanel(a, "Archery Range"));
    buildingsPanel.add(new MilitaryBuildingPanel(a, "Stable"));

  }

}
