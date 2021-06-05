package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.MilitaryBuilding;
import exceptions.FriendlyFireException;
import exceptions.InvalidUnitException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
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

  public static City searchForCity(String cityName, ArrayList<City> availableCities) throws NullPointerException {
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

  public boolean pairEqual(String firstCity, String secondCity, Distance distance) {
    return distance.getFrom().equals(firstCity) && distance.getTo().equals(secondCity);
  }

  public int searchForDistance(String x, String y) {
    for (Distance distance : distances) {
      if (pairEqual(x, y, distance) || pairEqual(y, x, distance)) {
        return distance.getDistance();
      }
    }
    return 0;
  }

  public void targetCity(Army army, String targetName) {
    String currentCity = army.getCurrentLocation();
    if (!army.getTarget().equals("")) {
      return;
    }
    int distance = searchForDistance(currentCity, targetName);
    army.setDistancetoTarget(distance);
    army.setTarget(targetName);
    army.setCurrentLocation("onRoad");
    army.setCurrentStatus(Status.MARCHING);
  }

  public void endTurn() {
    currentTurnCount++;

    clearBuildings();
    feedArmy();
    handleTarget();
    updateSiege();
  }

  private void updateSiege() {
    for (City city : availableCities) {
      if (city.isUnderSiege()) {
        if (city.getTurnsUnderSiege() == 3){
          city.setUnderSiege(false);
        }
        else{
         city.getDefendingArmy().killUnits();
         city.incTurnsUnderSiege();
        } 
      }
    }
  }

  private void handleTarget() {
    for (Army army : player.getControlledArmies()) {
      if (!army.getTarget().equals("")) {
        army.decTargetDistance();
        if (army.getDistancetoTarget() == 0) {
          army.setCurrentLocation(army.getTarget());
          army.setTarget("");
          army.setCurrentStatus(Status.IDLE);
        }
      }
    }
  }

  private void feedArmy() {
    double foodNeeded = 0;
    for (Army army : player.getControlledArmies()) {
      foodNeeded += army.foodNeeded();
    }
    player.setFood(player.getFood() - foodNeeded);

    if (player.getFood() <= 0) {
      player.setFood(0);
      for (Army army : player.getControlledArmies()) {
        army.killUnits();
      }
    }
  }

  private void clearBuildings() {
    for (City city : player.getControlledCities()) {
      for (MilitaryBuilding militaryBuilding : city.getMilitaryBuildings()) {
        militaryBuilding.setCoolDown(false);
        militaryBuilding.setCurrentRecruit(0);
      }
      for (EconomicBuilding economicBuilding : city.getEconomicalBuildings()) {
        economicBuilding.setCoolDown(false);
        if (economicBuilding instanceof Farm) {
          player.setFood(player.getFood() + economicBuilding.harvest());
        } else {
          player.setTreasury(player.getTreasury() + economicBuilding.harvest());
        }
      }
    }
  }

  public void occupy(Army a, String cityName) {
    City city = searchForCity(cityName, availableCities);
    player.addControlCity(city);
    if (city == null) {
      return;
    }
    city.setDefendingArmy(a);
    city.setUnderSiege(false);
    city.setTurnsUnderSiege(-1);
    a.setCurrentStatus(Status.IDLE);
  }

  public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
    if (player.getControlledArmies().contains(attacker) && player.getControlledArmies().contains(defender)) {
      throw new FriendlyFireException();
    }
    boolean attackerTurn = true;
    while (!attacker.getUnits().isEmpty() && !defender.getUnits().isEmpty()) {
      Unit attackerUnit = attacker.getRandomUnit();
      Unit defenderUnit = defender.getRandomUnit();
      if (attackerTurn) {
        attackerUnit.attack(defenderUnit);
      } else {
        defenderUnit.attack(attackerUnit);
      }
      attackerTurn = !attackerTurn;
    }
    if (defender.getUnits().isEmpty()) {
      occupy(attacker, defender.getCurrentLocation());
    } else {
      player.getControlledArmies().remove(attacker);
      City currentCity = searchForCity(defender.getCurrentLocation(), availableCities);
      currentCity.setTurnsUnderSiege(-1);
      currentCity.setUnderSiege(false);
    }
  }

  public boolean isGameOver() {
    if (currentTurnCount > maxTurnCount) {
      return true;
    }
    return (availableCities.size() == player.getControlledCities().size());
  }

}
