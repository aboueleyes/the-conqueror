package engine;

import utlis.ReadingCSVFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private Player player; // The current player of the game

  private ArrayList<City> availableCities; // An ArrayList containing the cities in the game, READ ONLY
  private ArrayList<Distance> distances; // An ArrayList containing the distances between the cities, READ ONLY
  private final int maxTurnCount = 30; // Maximum number of turns in the Game
  private int currentTurnCount = 1; // Current number of turns, READ ONLY


  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public ArrayList<City> getAvailableCities() {
    return this.availableCities;
  }

  public ArrayList<Distance> getDistances() {
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

  public Game(String playerName, String cityName) throws IOException{
    this.player = new Player(playerName);
    distances = new ArrayList<>();
  }

  // Load distances.csv file with format from,to,distance and initialise distances,availableCities attributes
  public void loadCitiesAndDistances() throws IOException{
    List<List<String>> data = ReadingCSVFile.readFile("distances.csv");
    
    for (List<String> line : data){
      String from = line.get(0);
      String to = line.get(1);
      int distance = Integer.parseInt(line.get(2));
      distances.add(new Distance(from, to, distance));

      if (!addedCity(from)) {
        availableCities.add(new City(from));
      }
      if (!addedCity(to)) {
        availableCities.add(new City(to));
      }
    }
  }

  // Check if a City is added before by comparing the name of the city with all cities in availableCities List
  public boolean addedCity(String cityName) {
    for (City city : availableCities) {
      if (city.getName().equals(cityName)) {
        return true;
      }
    }
    return false;
  }

}
