package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import engine.Game;
import exceptions.InvalidUnitException;
import views.StartView;

public class Controller implements ActionListener{
  Game game;
  StartView startview;
  public  Controller() {
	startview = new StartView(this);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			String playerName = startview.getNameOfPlayer().getText();
			String cityName = (String) startview.getCityOfPlayer().getSelectedItem();
			try {
				game = new Game(playerName, cityName);
				System.out.println(game.getPlayer().getControlledCities().get(0).getName());
				System.out.println("yarab");
			} catch (IOException e1) {
				
			} catch (InvalidUnitException e1) {
				
			}
		}
	}
	public static void main(String[] args) {
	  new Controller();
	}

}
