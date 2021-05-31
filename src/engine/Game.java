package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import exceptions.InvalidUnitException;
import exceptions.TargetNotReachedException;
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

  public Game(String playerName, String cityName) throws IOException, InvalidUnitException {
    this.player = new Player(playerName);
    distances = new ArrayList<>();
    availableCities = new ArrayList<>();
    loadCitiesAndDistances();

    for (City city : availableCities) {
      if (!city.getName().equals(cityName)) {
        String path = city.getName().toLowerCase() + "_army.csv";
        loadArmy(city.getName(), path);
      } else {
        player.addControlCity(city);
      }
    }
  }

  public void loadCitiesAndDistances() throws IOException {
    List<List<String>> data = ReadingCSVFile.readFile("distances.csv");

    for (List<String> line : data) {
      String from = line.get(0);
      String to = line.get(1);
      int distance = Integer.parseInt(line.get(2));

      distances.add(new Distance(from, to, distance));
      addToSet(new City(to));
      addToSet(new City(from));
    }
  }

  private void addToSet(City city) {
    if (!availableCities.contains(city)) {
      availableCities.add(city);
    }
  }

  public void loadArmy(String cityName, String path) throws IOException, InvalidUnitException {
    ArrayList<Unit> unitList = new ArrayList<>();
    List<List<String>> data = ReadingCSVFile.readFile(path);
    City currentCity = searchForCity(cityName, availableCities);

    Army army = new Army(cityName);
    for (List<String> line : data) {
      String unitName = line.get(0);
      int level = Integer.parseInt(line.get(1));
      setUnitType(unitList, unitName, level, army);
    }
    army.setUnits(unitList);
    if (currentCity != null) {
      currentCity.setDefendingArmy(army);
    }
  }

  public static City searchForCity(String cityName, ArrayList<City> availableCities) {
    for (City city : availableCities) {
      if (cityName.equals(city.getName())) {
        return city;
      }
    }
    return null;
  }

  private void setUnitType(ArrayList<Unit> unitList, String unitName, int level, Army army)
      throws InvalidUnitException {
    switch (unitName) {
      case "Archer":
        Unit archer = new Archer(level);
        archer.setParentArmy(army);
        unitList.add(archer);
        break;
      case "Infantry":
        Unit infantry = new Infantry(level);
        infantry.setParentArmy(army);
        unitList.add(infantry);
        break;
      case "Cavalry":
        Unit cavalry = new Cavalry(level);
        cavalry.setParentArmy(army);
        unitList.add(cavalry);
        break;
      default:
        throw new InvalidUnitException();
    }
  }

  public int searchForDistance(String x, String y) {
    for (Distance distance : distances) {
      if (distance.getFrom().equals(x) && distance.getTo().equals(y)) {
        return distance.getDistance();
      }
    }
    return 0;
  }

  public void targetCity(Army army, String targetName) {
    // TODO update status , exception
    String currentCity = army.getCurrentLocation();
    if (army.getDistancetoTarget() > 0) {
      return;
    }
    int distanceToX = searchForDistance(currentCity, targetName);
    int distanceToY = searchForDistance(targetName, currentCity);
    int distance = distanceToX + distanceToY;
    army.setDistancetoTarget(distance);
  }

  public void endTurn() {
    currentTurnCount++;

    for (City city : availableCities) {
      for (MilitaryBuilding militaryBuilding : city.getMilitaryBuildings()) {
        militaryBuilding.setCoolDown(false);
        militaryBuilding.setCurrentRecruit(0);
      }
      for (EconomicBuilding economicBuilding : city.getEconomicalBuildings()) {
        economicBuilding.setCoolDown(false);
        player.setFood(player.getFood() + economicBuilding.harvest());
      }
    }
  }
}
