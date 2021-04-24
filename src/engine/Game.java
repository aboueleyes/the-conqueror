package engine;

import java.util.ArrayList;

public class Game {
  
  private Player player; //The current player of the game
  private ArrayList<City> availableCities; // An ArrayList containing the cities in the game, READ ONLY
  private ArrayList<Distance> distances; //An ArrayList containing the distances between the cities, READ ONLY
  private final int maxTurnCount = 30; //Maximum number of turns in the Game
  private int currentTurnCount = 1; //Current number of turns, READ ONLY
  
  public Player getPlayer() {
	  return this.player;
  }
  
  public void setPlayer(Player player) {
	  this.player = player;
  }
  
  public ArrayList<City> getAvailabeCities(){
	  return this.availableCities;
  }
  
  public ArrayList<Distance> getDistances(){
	  return this.distances;
  }
  
  public int getMaxTurnCount() {
	  return this.maxTurnCount;
  }
  
  public int getCurrentTurnCount() {
	  return this.currentTurnCount;
  }
  
  public void setCurrentTurnCount(int currentTurnCount) {
	  this.currentTurnCount = currentTurnCount;
  }
  
  

}
