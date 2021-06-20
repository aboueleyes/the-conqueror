package controllers;

import static javax.swing.JOptionPane.showMessageDialog;
import static views.view.CityView.BUILDING_NAMES;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import buildings.Building;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.PlayerListener;
import exceptions.BuildingInCoolDownException;
import exceptions.InvalidBuildingException;
import exceptions.InvalidUnitException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.ArmyListener;
import units.Unit;
import views.button.CityButton;
import views.button.UnitButton;
import views.panel.ArmyPanel;
import views.panel.MilitaryBuildingPanel;
import views.panel.PlayerPanel;
import views.panel.UnitPanel;
import views.view.CityView;
import views.view.StartView;
import views.view.WorldMapView;

public class Controller implements ActionListener, GameListener, PlayerListener, ArmyListener {
	Game game;
	StartView startView;
	WorldMapView worldMapView;
	PlayerPanel[] playerPanels = new PlayerPanel[5];
	CityView[] cityViews = new CityView[3];
	public static final String[] CITIES_NAMES = { "Cairo", "Rome", "Sparta" };
	protected static final String[] UNITS_NAMES = { "Infantry", "Cavalry", "Archer" };

	public Controller() {
		startView = new StartView(this);
		for (int i = 0; i < playerPanels.length; i++) {
			playerPanels[i] = new PlayerPanel(this);
		}
		worldMapView = new WorldMapView(this, playerPanels[0]);
	}

	public int getIndexOfCity(String name) {
		for (int i = 0; i < CITIES_NAMES.length; i++) {
			if (CITIES_NAMES[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			startGame();
			if (e.getActionCommand().equals("End Turn")) {
				game.endTurn();
				for (PlayerPanel playerPanel : playerPanels) {
					playerPanel.getNumOfTurns().setText("" + game.getCurrentTurnCount());
				}
			}
		}
		viewButtonsAction(e);
		setBuildButtonsAction(e);
		setRecruitButtonsAction(e);
	}

	private void setInitaiteButtonAction(ActionEvent e) {
		if (e.getActionCommand().equals("Initiate Army")) {
			UnitButton unitButton = (UnitButton) e.getSource();
			Unit unit = unitButton.getUnit();
			City city = Game.searchForCity(unit.getParentArmy().getCurrentLocation(), game.getAvailableCities());
			game.getPlayer().initiateArmy(city, unit);
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
					e1.printStackTrace();
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
						e1.printStackTrace();
					}
				} else {
					Building building = button.getCity().searchForBuilding(BUILDING_NAMES[i]);
					try {
						game.getPlayer().upgradeBuilding(building, button.getCity());
					} catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException e1) {
						e1.printStackTrace();
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

	private void viewButtonsAction(ActionEvent e) {
		for (int i = 0; i < CITIES_NAMES.length; i++) {
			if (e.getActionCommand().equals(CITIES_NAMES[i])) {
				System.out.println("leko");
				worldMapView.setVisible(false);
				cityViews[i].setVisible(true);
			}
		}
	}

	private void startGame() throws NullPointerException {
		String playerName = startView.getNameOfPlayer().getText();
		String cityName = (String) startView.getCityOfPlayer().getSelectedItem();
		try {
			game = new Game(playerName, cityName);
		} catch (IOException | InvalidUnitException e1) {
			showMessageDialog(null, "Error in csv files Existing!!");
			System.exit(1);
		}
		game.setGameListener(this);
		game.getPlayer().setPlayerListener(this);
		cityUnderControl(cityName);
		startView.dispose();
		worldMapView.setVisible(true);
		setPlayer();
		for (int i = 0; i < CITIES_NAMES.length; i++) {
			cityViews[i] = new CityView(this, playerPanels[i + 1],
					Game.searchForCity(CITIES_NAMES[i], game.getAvailableCities()));
		}
	}

	public void setPlayer() {
		for (PlayerPanel playerPanel : playerPanels) {
			playerPanel.getPlayerName().setText(game.getPlayer().getName());
			playerPanel.getPlayerGold().setText(Double.toString(game.getPlayer().getTreasury()));
			playerPanel.getPlayerFood().setText(Double.toString(game.getPlayer().getFood()));
			playerPanel.getNumOfTurns().setText(Integer.toString(game.getCurrentTurnCount()));
		}
	}

	public void cityUnderControl(String cityName) {
		if (cityName.equals("Cairo")) {
			worldMapView.getCairoButton().setEnabled(true);
		} else if (cityName.equals("Rome")) {
			worldMapView.getRomeButton().setEnabled(true);
		} else {
			worldMapView.getSpartaButton().setEnabled(true);
		}
	}

	public static void main(String[] args) {
		new Controller();
	}

	@Override
	public void onKill(Army army) {
		// TODO Auto-generated method stub

	}

	public int getIndexOfBuilding(String type) {
		for (int i = 0; i < BUILDING_NAMES.length; i++) {
			if (type.equals(BUILDING_NAMES[i])) {
				return i;
			}
		}
		return -1;
	}

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
		UnitPanel unitPanel = new UnitPanel(this, unit);
		unit.setUnitPanel(unitPanel);
		getCityView(city).getUnitsCards().addCard(unitPanel);
	}

	private CityView getCityView(City city) {
		return cityViews[getIndexOfCity(city.getName())];
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
		getCityView(city).getArmyCards().addCard(armyPanel);
	}

	@Override
	public void onSiegeing(Army army, City city) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTargetCity(Army army, City city) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSiegeUpdate(City city, Army army) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attackY3am(City city, Army army) {
		// TODO Auto-generated method stub
	}

	@Override
	public void armyArrived(Army army) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDistanceUpdated(Army army) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFeedUpdated() {
		for (PlayerPanel playerPanel : playerPanels) {
			playerPanel.getPlayerFood().setText("" + game.getPlayer().getFood());
		}
	}

	@Override
	public void onOccupy(City city, Army army) {
		// TODO Auto-generated method stub

	}

}
