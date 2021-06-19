package controllers;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.InputVerifier;
import engine.Game;
import engine.GameListener;
import engine.Player;
import engine.PlayerListener;
import exceptions.InvalidUnitException;
import views.CityView;
import views.PlayerPanel;
import views.StartView;
import views.WorldMapView;

public class Controller implements ActionListener, GameListener, PlayerListener {
	Game game;
	StartView startView;
	WorldMapView worldMapView;
	PlayerPanel playerPanel;
	PlayerPanel playerPanel2;
	CityView cityView;

	public Controller() throws FontFormatException, IOException {
		startView = new StartView(this);
		playerPanel = new PlayerPanel(this);
		playerPanel2 = new PlayerPanel(this);
		worldMapView = new WorldMapView(this, playerPanel);
		cityView = new CityView(this, playerPanel2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			System.out.println(startView.getNameOfPlayer().getInputVerifier());
			String playerName = startView.getNameOfPlayer().getText();
			System.out.println(playerName);
			String cityName = (String) startView.getCityOfPlayer().getSelectedItem();
			try {
				game = new Game(playerName, cityName);
				System.out.println(cityName);
				cityUnderControl(cityName);
				// System.out.println(game.getPlayer().getControlledCities().get(0).getName());
				System.out.println("Game Started");
				startView.dispose();
				worldMapView.setVisible(true);
				setPlayer();

			} catch (IOException | InvalidUnitException e1) {
				showMessageDialog(null, "Error in csv files Existing!!");
				System.err.println("Error in csv");
				System.exit(1);

			}
		}
		if (e.getActionCommand().equals("End Turn")) {
			System.out.println("Hello");
			game.setCurrentTurnCount(game.getCurrentTurnCount() + 1);
			playerPanel.getNumOfTurns().setText("" + game.getCurrentTurnCount());
		}
		if (e.getActionCommand().equals("view")) {
			worldMapView.dispose();
			cityView.setVisible(true);

		}

	}

	public void setPlayer() {
		playerPanel.getPlayerName().setText(game.getPlayer().getName());
		playerPanel.getPlayerGold().setText(Double.toString(game.getPlayer().getTreasury()));
		playerPanel.getPlayerFood().setText(Double.toString(game.getPlayer().getFood()));
		playerPanel.getNumOfTurns().setText(Integer.toString(game.getCurrentTurnCount()));
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

}
