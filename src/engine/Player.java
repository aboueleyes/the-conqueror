package engine;

import java.util.ArrayList;

import units.Army;

public class Player {
	
  private String name; //Variable representing the name of the player, READ ONLY
  private ArrayList<City> controlledCiteis; // An ArrayList containing the player’s controlled cities, READ ONLY
  private ArrayList<Army> controlledArmies; //An ArrayList containing the player’s controlled armies, READ ONLY
  double treasury; //The amount of gold the player has
  double food; //The amount of food the player has
  
  public String getName() {
	  return this.name;
  }
  
  public ArrayList<City> getControlledCities(){
	  return this.controlledCiteis;
  }
  
  public ArrayList<Army> getControlledArmies(){
	  return this.controlledArmies;
  }
  
  public double getTreasury() {
	  return this.treasury;
  }
  
  public void setTreasury(double treasury) {
	  this.treasury = treasury;
  }
  
  public double getFood() {
	  return this.food;
  }
  
  public void setFood(double food) {
	  this.food = food;
  }
  
  public Player(String name) {
	  this.name = name;
	  controlledCiteis = new ArrayList<>();
	  controlledArmies = new ArrayList<>();
  }

}
