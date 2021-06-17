package controllers;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.InputVerifier;
import engine.Game;
import engine.Player;
import exceptions.InvalidUnitException;
import views.PlayerPanel;
import views.StartView;
import views.WorldMapView;

public class Controller implements ActionListener {
	Game game;
	StartView startView;
    WorldMapView worldMapView;
	PlayerPanel playerPanel;
	public Controller() throws FontFormatException, IOException {
		startView = new StartView(this);
		playerPanel = new PlayerPanel(this);
		worldMapView = new WorldMapView(this,playerPanel);
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
		if (e.getActionCommand().equals("End Turn")){
			game.setCurrentTurnCount(game.getCurrentTurnCount()+1);
			playerPanel.getNumOfTurns().setText(""+game.getCurrentTurnCount());
		}
		
	}
    public void setPlayer(){
		playerPanel.getPlayerName().setText(game.getPlayer().getName());
		playerPanel.getPlayerGold().setText(Double.toString(game.getPlayer().getTreasury()));
		playerPanel.getPlayerFood().setText(Double.toString(game.getPlayer().getFood()));
		playerPanel.getNumOfTurns().setText(Integer.toString(game.getCurrentTurnCount()));
	}  
	public void cityUnderControl(String cityName){
		if (cityName.equals("Cairo")){
			worldMapView.getCairoButton().setEnabled(true);
		}
		else if(cityName.equals("Rome")){
			worldMapView.getRomeButton().setEnabled(true);
		}
		else{
			worldMapView.getSpartaButton().setEnabled(true);
		}
	}
	public static void main(String[] args) throws FontFormatException, IOException {
		new Controller();
	}

}
