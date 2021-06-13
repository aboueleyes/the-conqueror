package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import engine.Game;
import exceptions.InvalidUnitException;
import views.StartView;

public class Controller implements ActionListener {
	Game game;
	StartView startView;

	public Controller() {
		startView = new StartView(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			String playerName = startView.getNameOfPlayer().getText();
			String cityName = (String) startView.getCityOfPlayer().getSelectedItem();
			try {
				game = new Game(playerName, cityName);
				System.out.println(game.getPlayer().getControlledCities().get(0).getName());
				System.out.println("Game Started");
			} catch (IOException | InvalidUnitException e1) {

			}
		}
	}

	public static void main(String[] args) {
		new Controller();
	}

}
