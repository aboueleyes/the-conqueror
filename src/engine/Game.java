package engine;

import java.util.List;

public class Game {

  private Player player; // The current player of the game

  private List<City> availableCities; // An ArrayList containing the cities in the game, READ ONLY
  private List<Distance> distances; // An ArrayList containing the distances between the cities, READ ONLY
  private final int maxTurnCount = 30; // Maximum number of turns in the Game
  private int currentTurnCount = 1; // Current number of turns, READ ONLY


  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public List<City> getAvailabeCities() {
    return this.availableCities;
  }

  public List<Distance> getDistances() {
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
