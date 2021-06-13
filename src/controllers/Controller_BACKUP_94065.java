package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;

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
      try {
      String playerName = startview.getNameOfPlayer().getText();
      String cityName = (String) startview.getCityOfPlayer().getSelectedItem();
      
      game = new Game(playerName, cityName);
      System.out.println(game.getPlayer().getControlledCities().get(0).getName());
      startview.dispose();
      } catch (IOException e1) {
        
      } catch (Exception e1) {
        
      }
      
    }
  }
  public static void main(String[] args) {
    new Controller();
  }

}
