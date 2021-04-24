package engine;

import java.util.ArrayList;
import java.util.List;

import units.Army;

public class Player {
	
  private String name; // Variable representing the name of the player, READ ONLY
  private List<City> controlledCities; // An ArrayList containing the player’s controlled cities, READ ONLY
  private List<Army> controlledArmies; // An ArrayList containing the player’s controlled armies, READ ONLY
  private double treasury; // The amount of gold the player has
  private double food; // The amount of food the player has
  
  public String getName() {
	  return this.name;
  }
  
  public List<City> getControlledCities(){
	  return this.controlledCities;
  }
  
  public List<Army> getControlledArmies(){
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
	  controlledCities = new ArrayList<>();
	  controlledArmies = new ArrayList<>();
  }

}
