package engine;

import utlis.ReadingCSVFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import units.Army;
import units.Unit;

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

  public Game(String playerName, String cityName) throws IOException {
    this.player = new Player(playerName);
    distances = new ArrayList<>();
    availableCities = new ArrayList<>();
    // loadCitiesAndDistances();
    //TODO
    // for (City city : availableCities){
    //   if (!city.getName().equals(cityName))
    //     city.setDefendingArmy(new Army(cityName));
  }
  
  // Load distances.csv file with format from,to,distance and initialise
  // distances,availableCities attributes
  public void loadCitiesAndDistances() throws IOException {
    List<List<String>> data = ReadingCSVFile.readFile("distances.csv");

    for (List<String> line : data) {
      String from = line.get(0);
      String to = line.get(1);
      int distance = Integer.parseInt(line.get(2));
      distances.add(new Distance(from, to, distance));
      if (!availableCities.contains(new City(from))) {
        availableCities.add(new City(from));
      }
      if (!availableCities.contains(new City(to))) {
        availableCities.add(new City(to));

      }
    }
  }
  public void loadArmy(String cityName, String path) throws IOException{
    // TODO
    // List<List<String>> data = ReadingCSVFile.readFile(path);
    // ArrayList<Unit> uniArrayList = new ArrayList<>();

    // for (List<String> line : data) {
    //   String unitName = line.get(0);
    //   int level = Integer.getInteger(line.get(1));
    //   uniArrayList.add(new Unit())
    //   City city = new City(cityName);
    //   Army army = new Army(cityName);
    //   city.setDefendingArmy(army);
      
    // }
  }
}
