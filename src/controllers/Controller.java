package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;

import engine.Game;
import exceptions.InvalidUnitException;
import views.StartView;
import views.WorldMapView;

public class Controller implements ActionListener {
	Game game;
	StartView startView;
    WorldMapView worldMapView;
	public Controller() {
		startView = new StartView(this);
		worldMapView = new WorldMapView();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			String playerName = startView.getNameOfPlayer().getText();
			String cityName = (String) startView.getCityOfPlayer().getSelectedItem();
			try {
				game = new Game(playerName, cityName);
				System.out.println(cityName);
				// System.out.println(game.getPlayer().getControlledCities().get(0).getName());
				System.out.println("Game Started");
				startView.dispose();
				worldMapView.setVisible(true);

			} catch (IOException | InvalidUnitException e1) {
				showMessageDialog(null, "Error in csv files Existing!!");
				System.err.println("Error in csv");
				System.exit(1);

			}
		}
	}

	public static void main(String[] args) {
		new Controller();
	}

}
