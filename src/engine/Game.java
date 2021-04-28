package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;
import utlis.ReadingCSVFile;

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
    loadCitiesAndDistances();

    for (City city : availableCities) {
      if (!city.getName().equals(cityName)) {
        String path = city.getName().toLowerCase() + "_city.csv";
        loadArmy(city.getName(), path);
      } else {
        player.addControlCity(city);
      }
    }
  }

  public void loadCitiesAndDistances() throws IOException {
    List<List<String>> data = ReadingCSVFile.readFile("distances.csv");
    City cityTo = null;
    City cityFrom = null;

    for (List<String> line : data) {
      String from = line.get(0);
      String to = line.get(1);
      int distance = Integer.parseInt(line.get(2));

      distances.add(new Distance(from, to, distance));
      cityTo = new City(to);
      cityFrom = new City(from);
      if (!availableCities.contains(cityFrom)) {
        availableCities.add(cityFrom);
      }
      if (!availableCities.contains(cityTo)) {
        availableCities.add(cityTo);

      }
    }
  }

  public void loadArmy(String cityName, String path) throws IOException {
    ArrayList<Unit> unitList = new ArrayList<>();
    List<List<String>> data = ReadingCSVFile.readFile(path);
    City currentCity = null;

    for (City city : availableCities) {
      if (cityName.equals(city.getName())) {
        currentCity = city;
        break;
      }
    }

    for (List<String> line : data) {
      String unitName = line.get(0);
      int level = Integer.parseInt(line.get(1));

      switch (unitName) {
      case "Archer":
        unitList.add(new Archer(level));
        break;
      case "Infantry":
        unitList.add(new Infantry(level));
        break;
      case "Cavalry":
        unitList.add(new Cavalry(level));
        break;
      default:
        break;
      }
    }
    Army army = new Army(cityName);
    army.setUnits(unitList);
    if (currentCity != null)
      currentCity.setDefendingArmy(army);

  }

}
