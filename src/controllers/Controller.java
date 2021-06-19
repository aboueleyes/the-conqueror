package controllers;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.InputVerifier;

import static views.CityView.BUILDING_NAMES;
import buildings.Building;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;
import engine.PlayerListener;
import exceptions.BuildingInCoolDownException;
import exceptions.InvalidUnitException;
import exceptions.MaxLevelException;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.ArmyListener;
import units.Unit;
import views.CityButton;
import views.CityView;
import views.MilitaryBuildingPanel;
import views.PlayerPanel;
import views.StartView;
import views.StyledButton;
import views.WorldMapView;

public class Controller implements ActionListener, GameListener, PlayerListener, ArmyListener {
	Game game;
	StartView startView;
	WorldMapView worldMapView;
	PlayerPanel[] playerPanels = new PlayerPanel[5];
	CityView[] cityViews = new CityView[3];
	private final String[] citiesNames = { "Cairo", "Rome", "Sparta" };

	public Controller() throws FontFormatException, IOException {
		startView = new StartView(this);
		for (int i = 0; i < playerPanels.length; i++) {
			playerPanels[i] = new PlayerPanel(this);
		}
		worldMapView = new WorldMapView(this, playerPanels[0]);
	}

	public int getIndexOfCity(String name) {
		for (int i = 0; i < citiesNames.length; i++) {
			if (citiesNames[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			try {
				startGame();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("End Turn")) {
			game.endTurn();
			for (PlayerPanel playerPanel : playerPanels) {
				playerPanel.getNumOfTurns().setText("" + game.getCurrentTurnCount());
			}
		}
		viewButtonsAction(e);
		setBuildButtonsAction(e);
	}

	private void setBuildButtonsAction(ActionEvent e) {
		for (int i = 0; i < BUILDING_NAMES.length; i++) {
			if (e.getActionCommand().equals(BUILDING_NAMES[i])) {
				CityButton button = (CityButton) e.getSource();
				if (!button.isBuilt()) {
					try {
						game.getPlayer().build(BUILDING_NAMES[i], button.getCity().getName());
						button.setText("Upgrade");
						button.setBuilt(true);
						if (i >= 2) {
							int index = getIndexOfCity(button.getCity().getName());
							MilitaryBuildingPanel panel = (MilitaryBuildingPanel) cityViews[index].getBuildlingsSlavePanels()[i];
							panel.setRecruitEnabled();
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

	private void viewButtonsAction(ActionEvent e) {
		if (e.getActionCommand().equals("rome")) {
			worldMapView.setVisible(false);
			cityViews[1].setVisible(true);
		}
		if (e.getActionCommand().equals("cairo")) {
			worldMapView.setVisible(false);
			cityViews[0].setVisible(true);
		}
		if (e.getActionCommand().equals("sparta")) {
			worldMapView.setVisible(false);
			cityViews[2].setVisible(true);
		}
	}

	private void startGame() throws NullPointerException, FontFormatException, IOException {
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
		cityViews[0] = new CityView(this, playerPanels[1], Game.searchForCity("Cairo", game.getAvailableCities()));
		cityViews[1] = new CityView(this, playerPanels[2], Game.searchForCity("Rome", game.getAvailableCities()));
		cityViews[2] = new CityView(this, playerPanels[3], Game.searchForCity("Sparta", game.getAvailableCities()));
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

	public static void main(String[] args) throws FontFormatException, IOException {
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
		if (city.getName().equals("Cairo")) {
			cityViews[0].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
			cityViews[0].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
		}
		if (city.getName().equals("Rome")) {
			cityViews[1].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
			cityViews[1].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
		}
		if (city.getName().equals("Sparta")) {
			cityViews[2].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
			cityViews[2].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void buildingUpgraded(Building building, City city) {
		String type = building.getType();
		if (city.getName().equals("Cairo")) {
			cityViews[0].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
			cityViews[0].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
		}
		if (city.getName().equals("Rome")) {
			cityViews[1].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
			cityViews[1].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
		}
		if (city.getName().equals("Sparta")) {
			cityViews[2].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setVisible(true);
			cityViews[2].getBuildlingsSlavePanels()[getIndexOfBuilding(type)].getInfo().setText(building.toString());
		}
	}

	@Override
	public void onInitiated(City city, Unit unit) {
		// TODO Auto-generated method stub

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
