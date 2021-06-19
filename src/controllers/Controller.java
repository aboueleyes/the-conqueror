package controllers;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.InputVerifier;

import buildings.Building;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;
import engine.PlayerListener;
import exceptions.InvalidUnitException;
import units.Army;
import units.ArmyListener;
import units.Unit;
import views.CityView;
import views.PlayerPanel;
import views.StartView;
import views.WorldMapView;

public class Controller implements ActionListener, GameListener, PlayerListener, ArmyListener {
	Game game;
	StartView startView;
	WorldMapView worldMapView;
	PlayerPanel[] playerPanels = new PlayerPanel[5];
	CityView[] cityViews = new CityView[3];

	public Controller() throws FontFormatException, IOException {
		startView = new StartView(this);
		playerPanels[0] = new PlayerPanel(this);
		playerPanels[1] = new PlayerPanel(this);
		playerPanels[2] = new PlayerPanel(this);
		playerPanels[3] = new PlayerPanel(this);
		playerPanels[4] = new PlayerPanel(this);
		worldMapView = new WorldMapView(this, playerPanels[0]);
		cityViews[0] = new CityView(this, playerPanels[1]);
		cityViews[1] = new CityView(this, playerPanels[2]);
		cityViews[2] = new CityView(this, playerPanels[3]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			startGame();
		}
		// if (e.getActionCommand().equals("End Turn")) {
		// game.setCurrentTurnCount(game.getCurrentTurnCount() + 1);
		// playerPanel.getNumOfTurns().setText("" + game.getCurrentTurnCount());
		// }
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

	private void startGame() {
		String playerName = startView.getNameOfPlayer().getText();
		String cityName = (String) startView.getCityOfPlayer().getSelectedItem();
		try {
			game = new Game(playerName, cityName);

		} catch (IOException | InvalidUnitException e1) {
			showMessageDialog(null, "Error in csv files Existing!!");
			System.exit(1);
		}
		cityUnderControl(cityName);
		startView.dispose();
		worldMapView.setVisible(true);
		setPlayer();
		cityViews[0].setCity(Game.searchForCity("Cairo", game.getAvailableCities()));
		cityViews[1].setCity(Game.searchForCity("Rome", game.getAvailableCities()));
		cityViews[2].setCity(Game.searchForCity("Sparta", game.getAvailableCities()));
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

	@Override
	public void onBuild(Building building, City city) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTreasuryUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unitRecruited(Unit unit, City city) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildingUpgraded(Building building, City city) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void onOccupy(City city, Army army) {
		// TODO Auto-generated method stub

	}

}
