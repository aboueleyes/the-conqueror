package controllers;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import static views.view.CityView.BUILDING_NAMES;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import buildings.Building;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.PlayerListener;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.InvalidBuildingException;
import exceptions.InvalidUnitException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.ArmyListener;
import units.Unit;
import units.UnitListener;
import views.button.ArmyButton;
import views.button.CityButton;
import views.button.UnitButton;
import views.panel.ArmyPanel;
import views.panel.DefendingUnitPanel;
import views.panel.MilitaryBuildingPanel;
import views.panel.PlayerPanel;
import views.panel.StationaryArmyPanel;
import views.view.BattleView;
import views.view.CityView;
import views.view.EndGameView;
import views.view.StartView;
import views.view.WorldMapView;

public class Controller implements ActionListener, GameListener, PlayerListener, ArmyListener, UnitListener {
  Game game;
  StartView startView;
  WorldMapView worldMapView;
  PlayerPanel[] playerPanels = new PlayerPanel[5];
  CityView[] cityViews = new CityView[3];
  BattleView battleView;
  EndGameView endGameView;
  public static final String[] CITIES_NAMES = { "Cairo", "Rome", "Sparta" };
  protected static final String[] UNITS_NAMES = { "Infantry", "Cavalry", "Archer" };

  public Controller() {
    startView = new StartView(this);
    endGameView = new EndGameView(this);
    for (int i = 0; i < playerPanels.length; i++) {
      playerPanels[i] = new PlayerPanel(this);
    }
    worldMapView = new WorldMapView(this, playerPanels[0]);
    playerPanels[0].remove(playerPanels[0].getBack());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    setInitiateButtonAction(e);
    startGame(e);
    setBackButtonActionResponse(e);
    setEndTurnButton(e);
    setViewButtonsAction(e);
    setBuildButtonsAction(e);
    setRecruitButtonsAction(e);
    setTargetButtonAction(e);
    setSeigeingButtonAction(e);
    setSelectButttonAction(e);
    setRelocateButtonAction(e);
    setStartBattleButtonAction(e);
    setViewButtonAction(e);
    setNextAndPreviousButtonsAction(e);
    if (battleView != null) {
      setSelectAttackButtonAction(e);
      setSelectDefenderButtonAction(e);
      setAttackButtonAction(e);
      setAutoResolveButtonAction(e);
    }
    endGame(e);

  }

  public void endGame(ActionEvent e) {
    if (e.getActionCommand().equals("end game"))
      System.exit(0);
    if (e.getActionCommand().equals("play again")) {
      startView.dispose();
      endGameView.dispose();
      new Controller();
    }
  }

  private void setNextAndPreviousButtonsAction(ActionEvent e) {
    if (e.getActionCommand().equals("next") || e.getActionCommand().equals("previous")) {
      worldMapView.getUnitsCard().clear();
      worldMapView.getUnitsCard().setVisible(false);
    }
  }

  private void setViewButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("view")) {
      worldMapView.getUnitsCard().clear();
      ArmyButton button = (ArmyButton) e.getSource();
      Army army = button.getArmy();
      for (Unit unit : army.getUnits()) {
        worldMapView.getUnitsCard().addCard(unit.getInfoUnitPanel());
      }
      worldMapView.getUnitsCard().setVisible(true);
    }
  }

  private void setAutoResolveButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("Auto Resolve")) {
      try {
        game.autoResolve(battleView.getAttackerArmy(), battleView.getDefenderArmy());
      } catch (FriendlyFireException e1) {
        showErrorMessage(e1);
      }

    }
  }

  private void setInitiateButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("Initiate Army")) {
      UnitButton unitButton = (UnitButton) e.getSource();
      Unit unit = unitButton.getUnit();
      City city = cityNameToObject(unit.getParentArmy().getCurrentLocation());
      game.getPlayer().initiateArmy(city, unit);
    }
  }

  private void startGame(ActionEvent e) throws NullPointerException {
    if (e.getActionCommand().equals("Start")) {
      String playerName = startView.getNameOfPlayer().getText();
      String cityName = (String) startView.getCityOfPlayer().getSelectedItem();
      try {
        game = new Game(playerName, cityName);
      } catch (IOException e1) {
        showMessageDialog(null, "Error in csv files Existing!!");
        System.exit(1);
      }
      game.setGameListener(this);
      game.getPlayer().setPlayerListener(this);
      worldMapView.enableButton(cityNameToObject(cityName));
      startView.dispose();
      worldMapView.setVisible(true);
      setPlayer();
      for (int i = 0; i < CITIES_NAMES.length; i++) {
        cityViews[i] = new CityView(this, playerPanels[i + 1], cityNameToObject(CITIES_NAMES[i]));
      }
    }
  }

  /**
   * Start Game helpers
   */

  public void setPlayer() {
    for (PlayerPanel playerPanel : playerPanels) {
      playerPanel.getPlayerName().setText(game.getPlayer().getName());
      playerPanel.getPlayerGold().setText(Double.toString(game.getPlayer().getTreasury()));
      playerPanel.getPlayerFood().setText(Double.toString(game.getPlayer().getFood()));
      playerPanel.getNumOfTurns().setText(Integer.toString(game.getCurrentTurnCount()));
    }
  }

  private void setAttackButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("Attack")) {
      try {
        battleView.getAttackingUnit().attack(battleView.getDefendingUnit());

      } catch (FriendlyFireException e1) {
        showErrorMessage(e1);
      }
      Unit defender = battleView.getDefenderArmy().getRandomUnit();
      Unit playerUnit = battleView.getAttackerArmy().getRandomUnit();
      if (!battleView.getAttackerArmy().getUnits().isEmpty()) {

        try {
          defender.attack(playerUnit);
        } catch (FriendlyFireException e1) {
          showErrorMessage(e1);
        }
      }
      game.battleEnded(battleView.getAttackerArmy(), battleView.getDefenderArmy());
      battleView.setDefendingUnit(null);
      battleView.setAttackingUnit(null);
      JButton button = (JButton) e.getSource();
      button.setEnabled(false);
    }
  }

  private void setSelectDefenderButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("selectDefender")) {
      UnitButton button = (UnitButton) e.getSource();
      battleView.setDefendingUnit(button.getUnit());
      if (battleView.getAttackingUnit() != null) {
        battleView.getAttack().setEnabled(true);
      }
    }
  }

  private void setSelectAttackButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("selectAttacker")) {
      UnitButton button = (UnitButton) e.getSource();
      battleView.setAttackingUnit(button.getUnit());
      if (battleView.getDefendingUnit() != null) {
        battleView.getAttack().setEnabled(true);
      }
    }
  }

  private void setStartBattleButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("Start Battle")) {
      ArmyButton button = (ArmyButton) e.getSource();
      City city = cityNameToObject((String)button.getArmy().getArmyPanel().getCities().getSelectedItem());
      try {
        game.startBattle(button.getArmy(), city);
        battleView = new BattleView(this, playerPanels[4], button.getArmy(), city.getDefendingArmy());
        battleView.setVisible(true);
        worldMapView.setVisible(false);
        playerPanels[4].getBack().setEnabled(false);
      } catch (FriendlyFireException | TargetNotReachedException e1) {
        showErrorMessage(e1);
      }
    }

  }

  private void setRelocateButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("Relocate")) {
      UnitButton unitButton = (UnitButton) e.getSource();
      Unit unit = unitButton.getUnit();
      String city = unit.getParentArmy().getCurrentLocation();
      System.out.println(unit.getParentArmy().getCurrentLocation());
      Army army = cityViews[getIndexOfCity(city)].getSelected();
      try {
        army.relocateUnit(unit);
      } catch (MaxCapacityException e1) {
        showErrorMessage(e1);
      }
    }
  }

  private void setSelectButttonAction(ActionEvent e) {
    if (e.getActionCommand().equals("Select")) {
      ArmyButton armyButton = (ArmyButton) e.getSource();
      CityView cityView = cityViews[getIndexOfCity(armyButton.getArmy().getCurrentLocation())];
      if (cityView.getSelected() != null) {
        cityView.getSelected().getStationaryArmyPanel().getSelectArmy().setEnabled(true);
      }
      cityView.setSelected(armyButton.getArmy());
      for (Unit unit : cityView.getCity().getDefendingArmy().getUnits()) {
        unit.getUnitPanel().enableRelocate();
      }
      armyButton.setEnabled(false);
      SwingUtilities.updateComponentTreeUI(cityView);
    }
  }

  private void setSeigeingButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("SiegeCity")) {
      ArmyButton armyButton = (ArmyButton) e.getSource();
      Army army = armyButton.getArmy();
      String targetName = (String) armyButton.getArmy().getArmyPanel().getCities().getSelectedItem();
      try {
        game.getPlayer().laySiege(army, cityNameToObject(targetName));
      } catch (TargetNotReachedException | FriendlyCityException | NullPointerException e1) {
        showErrorMessage(e1);
      }
    }
  }

  private void setBackButtonActionResponse(ActionEvent e) {
    if (e.getActionCommand().equals("Back")) {
      for (Window window : Window.getWindows()) {
        window.setVisible(false);
      }
      worldMapView.setVisible(true);
      worldMapView.getUnitsCard().setVisible(false);
    }

  }

  private void setTargetButtonAction(ActionEvent e) {
    if (e.getActionCommand().equals("TargetCity")) {
      ArmyButton button = (ArmyButton) e.getSource();
      String targetName = (String) button.getArmy().getArmyPanel().getCities().getSelectedItem();
      CityView cityView = getCityView(button.getArmy().getCurrentLocation());
      game.targetCity(button.getArmy(), targetName);
      if (cityView != null && cityView.getSelected() != null) {
        if (cityView.getSelected().equals(button.getArmy())) {
          cityView.setSelected(null);
        }
        for (Unit unit : cityView.getCity().getDefendingArmy().getUnits()) {
          unit.getUnitPanel().disableRelocate();
        }
      }
    }
  }

  private void setEndTurnButton(ActionEvent e) {
    if (e.getActionCommand().equals("End Turn")) {
      game.endTurn();
      for (PlayerPanel playerPanel : playerPanels) {
        playerPanel.getNumOfTurns().setText("" + game.getCurrentTurnCount());
      }
    }
  }

  private void setRecruitButtonsAction(ActionEvent e) {
    for (int i = 2; i < BUILDING_NAMES.length; i++) {
      if (e.getActionCommand().equals("r" + BUILDING_NAMES[i])) {
        CityButton button = (CityButton) e.getSource();
        String unitType = UNITS_NAMES[i - 2];
        try {
          game.getPlayer().recruitUnit(unitType, button.getCity().getName());
        } catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException
            | InvalidBuildingException e1) {
          showErrorMessage(e1);
        }
      }
    }
  }

  private void setBuildButtonsAction(ActionEvent e) {
    for (int i = 0; i < BUILDING_NAMES.length; i++) {
      if (e.getActionCommand().equals(BUILDING_NAMES[i])) {
        CityButton button = (CityButton) e.getSource();
        if (!button.isBuilt()) {
          try {
            buildUpgrade(i, button);
            if (i >= 2) {
              enableRecuritButton(i, button);
            }
          } catch (NotEnoughGoldException e1) {
            showErrorMessage(e1);
          }
        } else {
          Building building = button.getCity().searchForBuilding(BUILDING_NAMES[i]);
          try {
            game.getPlayer().upgradeBuilding(building, button.getCity());
          } catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
            showErrorMessage(e1);
          }
        }
      }
    }
  }

  private void buildUpgrade(int i, CityButton button) throws NotEnoughGoldException {
    game.getPlayer().build(BUILDING_NAMES[i], button.getCity().getName());
    button.setText("Upgrade");
    button.setBuilt(true);
  }

  private void enableRecuritButton(int i, CityButton button) {
    int index = getIndexOfCity(button.getCity().getName());
    MilitaryBuildingPanel panel = (MilitaryBuildingPanel) cityViews[index].getBuildlingsSlavePanels()[i];
    panel.setRecruitEnabled();
  }

  private void setViewButtonsAction(ActionEvent e) {
    for (int i = 0; i < CITIES_NAMES.length; i++) {
      if (e.getActionCommand().equals(CITIES_NAMES[i])) {
        worldMapView.setVisible(false);
        cityViews[i].setVisible(true);
      }
    }
  }

  /**
   * Listeners Methods
   */

  @Override
  public void onBuild(Building building, City city, String type) {
    for (int i = 0; i < CITIES_NAMES.length; i++) {
      if (city.getName().equals(CITIES_NAMES[i])) {
        cityViews[i].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
        cityViews[i].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
      }
    }
  }

  @Override
  public void onTreasuryUpdate() {
    for (PlayerPanel playerPanel : playerPanels) {
      playerPanel.getPlayerGold().setText("" + game.getPlayer().getTreasury());
    }
  }

  @Override
  public void unitRecruited(Unit unit, City city) {
    getCityView(city).addDefendingPanel(unit, this);
    unit.setUnitListener(this);
    unit.addInfoUnitPanel(this);

  }

  @Override
  public void buildingUpgraded(Building building, City city) {
    String type = building.getType();
    for (int i = 0; i < CITIES_NAMES.length; i++) {
      if (city.getName().equals(CITIES_NAMES[i])) {
        cityViews[i].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
        cityViews[i].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
      }
    }
  }

  @Override
  public void onInitiated(City city, Unit unit, Army army) {
    ArmyPanel armyPanel = new ArmyPanel(this, army);
    army.setArmyListener(this);
    StationaryArmyPanel stationaryArmyPanel = new StationaryArmyPanel(this, army);
    getCityView(city).getArmyCards().addCard(stationaryArmyPanel);
    worldMapView.getArmyCards().addCard(armyPanel);
    armyPanel.getInfo().setText(game.toString(army));
    army.setArmyPanel(armyPanel);
    army.setStationaryArmyPanel(stationaryArmyPanel);
    stationaryArmyPanel.getInfo().setText(game.toString(army));
    getCityView(city).getUnitsCards().removeCard(unit.getUnitPanel());
    SwingUtilities.updateComponentTreeUI(getCityView(city));
  }

  @Override
  public void onSiegeing(Army army, City city) {
    updateArmyInformation(army);
  }

  @Override
  public void onTargetCity(Army army, City city) {
    if (city != null) {
      getCityView(city).getArmyCards().removeCard(army.getStationaryArmyPanel());
    }
    updateArmyInformation(army);

  }

  @Override
  public void onSiegeUpdate(City city, Army army) {
    updateArmyInformation(army);

  }

  @Override
  public void attackY3am(City city, Army army) {
    for (Window window : Window.getWindows()) {
      window.setVisible(false);
    }
    playerPanels[4].getBack().setEnabled(false);
    battleView = new BattleView(this, playerPanels[4], army, city.getDefendingArmy());
    battleView.setVisible(true);

  }

  @Override
  public void armyArrived(Army army) {
    updateArmyInformation(army);
  }

  @Override
  public void onDistanceUpdated(Army army) {
    updateArmyInformation(army);

  }

  @Override
  public void onFeedUpdated() {
    for (PlayerPanel playerPanel : playerPanels) {
      String food = String.format("$%.2f", game.getPlayer().getFood());
      playerPanel.getPlayerFood().setText(food);
    }
  }

  @Override
  public void onOccupy(City city, Army army) {
    worldMapView.enableButton(city);
    worldMapView.getArmyCards().removeCard(army.getArmyPanel());
    for (Unit unit : army.getUnits()) {
      getCityView(city).getUnitsCards().addCard(unit.getUnitPanel());
    }
  }

  @Override
  public void onRelocate(Army army, Unit unit) {
    String city = army.getCurrentLocation();
    cityViews[getIndexOfCity(city)].getUnitsCards().removeCard(unit.getUnitPanel());
    updateComponentTreeUI(cityViews[getIndexOfCity(city)]);
    updateArmyInformation(army);
  }

  @Override
  public void onRemovedUnit(Army army, Unit unit) {
    if (game.getPlayer().getControlledArmies().contains(unit.getParentArmy())) {
      battleView.getAttackerPanel().removeCard(unit.getBattleUnitPanel());
    } else {
      battleView.getDefenderPanel().removeCard(unit.getBattleUnitPanel());
    } // cityViews[getIndexOfCity(unit.getParentArmy().getCurrentLocation())].getUnitsCards().remove(unit.getUnitPanel());
    updateComponentTreeUI(battleView);

  }

  @Override
  public void UnitOnattack(Unit attackerUnit, Unit defenderUnit, int killedSoldiers) {
    String toBeLogged = "Attacked unit lose " + killedSoldiers + " Soldiers" + "\n";
    battleView.getLog().setText(battleView.getLog().getText() + toBeLogged);
    defenderUnit.getBattleUnitPanel().getInfo().setText(defenderUnit.toString());

  }

  /**
   * Helper Methods
   */

  private CityView getCityView(City city) {
    return cityViews[getIndexOfCity(city.getName())];
  }

  private CityView getCityView(String name) {
    if(getIndexOfCity(name)>=0){
    return cityViews[getIndexOfCity(name)];
    }
    return null;
  }

  public int getIndexOfBuilding(String type) {
    for (int i = 0; i < BUILDING_NAMES.length; i++) {
      if (type.equals(BUILDING_NAMES[i])) {
        return i;
      }
    }
    return -1;
  }

  public int getIndexOfCity(String name) {
    for (int i = 0; i < CITIES_NAMES.length; i++) {
      if (CITIES_NAMES[i].equals(name)) {
        return i;
      }
    }
    return -1;
  }

  private void showErrorMessage(Exception e1) {
    showMessageDialog(null, e1.getMessage());
  }

  private void updateArmyInformation(Army army) {
    army.getArmyPanel().getInfo().setText(game.toString(army));
    army.getStationaryArmyPanel().getInfo().setText(game.toString(army));
  }

  private City cityNameToObject(String cityName) {
    return Game.searchForCity(cityName, game.getAvailableCities());
  }

  public static void main(String[] args) {
    new Controller();
  }

  @Override
  public void playerWon() {
    endGame(true);

  }

  @Override
  public void playerLost() {
    endGame(false);

  }

  public void endGame(boolean youWon) {
    JLabel text;
    if (youWon)
      text = new JLabel("congrantulations, you won the game");
    else
      text = new JLabel("opps, you lost the game");
    text.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    text.setOpaque(false);
    endGameView.addLabel(text);
    worldMapView.dispose();
    if (battleView != null)
      battleView.dispose();
    for (CityView currentCity : cityViews)
      currentCity.dispose();
    endGameView.setVisible(true);
  }

  @Override
  public void OnBattleEnded(Army attacker, Army defender, boolean win) {
    if (win) {
      showMessageDialog(null, "You won The battle");
      worldMapView.getArmyCards().removeCard(attacker.getArmyPanel());
    } else {
      worldMapView.getArmyCards().removeCard(attacker.getArmyPanel());
      showMessageDialog(null, "you lose The battle");
    }
    playerPanels[4].getBack().setEnabled(true);
    battleView.getAutoResolve().setEnabled(false);
    battleView.getAttack().setEnabled(false);

  }

  @Override
  public void OnUpdateSoldierCount(Unit unit) {
    if(unit.getUnitPanel()!=null&& unit.getInfoUnitPanel()!=null){
    unit.getUnitPanel().getInfo().setText(unit.toString());
    unit.getInfoUnitPanel().getInfo().setText(unit.toString());
    }
  }
}
