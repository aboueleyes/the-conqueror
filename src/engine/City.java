package engine;

import java.util.ArrayList;
import java.util.List;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

public class City {
	
  private String name; // The name of the city, READ  ONLY
  private List<EconomicBuilding> economicalBuildings; // An ArrayList containing the economical building in the city, READ ONLy
  private List<MilitaryBuilding> militaryBuildings; // An ArrayList containing the military building in the city, READ ONLY
  private Army defendingArmy; // The defending army of the city
  private int turnsUnderSiege; // Number of turns the city has been siege
  private boolean underSiege = false; // Variable checking if the is under siege or not

  public String getName() {
	  return this.name;
  }
  
  public List<EconomicBuilding> getEconomicalBuildings() {
	  return this.economicalBuildings;
  }
  
  public List<MilitaryBuilding> getMilitaryBuildings(){
	  return this.militaryBuildings;
  }
  
  public Army getDefendignArmy() {
	  return this.defendingArmy;
  }
  
  public void setDefendingArmy(Army defendingArmy) {
	  this.defendingArmy = defendingArmy;
  }
  
  public int getTurnsUnderSiege() {
	  return this.turnsUnderSiege;
  }
  
  public void setTurnsUnderSiege(int trunsUnderSiege) {
	  this.turnsUnderSiege = trunsUnderSiege;
  }
  
  public boolean isUnderSiege() {
	  return this.underSiege;
  }
  
  public void setUnderSiege(boolean underSiege) {
	  this.underSiege = underSiege;
  }
  
  public City(String name) {
	  this.name = name;
	  economicalBuildings = new ArrayList<>();
	  militaryBuildings = new ArrayList<>();
	  defendingArmy = new Army();
  }
  
}
