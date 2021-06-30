package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import buildings.EconomicBuilding;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyFireException;
import exceptions.InvalidBuildingException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;
import utlis.ReadingCSVFile;

public class Game {

  private static final String NUMBER_OF_UNITS = "Number Of Units: ";

  private Player player; // The current player of the game

  private ArrayList<City> availableCities; // An ArrayList containing the cities in the game, READ ONLY
  private ArrayList<Distance> distances; // An ArrayList containing the distances between the cities, READ ONLY
  private final int maxTurnCount = 50; // Maximum number of turns in the Game
  private int currentTurnCount = 1; // Current number of turns, READ ONLY
  private static final double INITIAL_TREASURY = 5000;
  private static final String ON_ROAD = "OnRoad";
  private GameListener gameListener;

  public Player getPlayer() {
    return player;
  }

  public GameListener getGameListener() {
    return gameListener;
  }

  public void setGameListener(GameListener gameListener) {
    this.gameListener = gameListener;
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

  public Game(String playerName, String cityName, String level) throws IOException {
    this.player = new Player(playerName);
    player.setTreasury(INITIAL_TREASURY);
    setCurrentTurnCount(1);
    distances = new ArrayList<>();
    availableCities = new ArrayList<>();
    loadCitiesAndDistances();
    loadCitiesFiles(cityName, level);
  }

  private void loadCitiesFiles(String cityName, String level) throws IOException {
    for (City city : availableCities) {
      if (!city.getName().equals(cityName)) {
        String path = "assets/csv/" + level + "/" + city.getName().toLowerCase() + "_army.csv";
        loadArmy(city.getName(), path);
      } else {
        player.addControlCity(city);
      }
    }
  }

  public void loadCitiesAndDistances() throws IOException {
    List<List<String>> data = ReadingCSVFile.readFile("./assets/csv/distances.csv");
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

  public void loadArmy(String cityName, String path) throws IOException {
    ArrayList<Unit> unitList = new ArrayList<>();
    List<List<String>> data = ReadingCSVFile.readFile(path);
    City currentCity = searchForCity(cityName, availableCities);
    Army army = new Army(cityName);
    readUnitValues(unitList, data, army);
    army.setUnits(unitList);
    if (currentCity != null) {
      currentCity.setDefendingArmy(army);
    }
    System.out.println(currentCity.getDefendingArmy().getUnits().size());
  }

  private void readUnitValues(ArrayList<Unit> unitList, List<List<String>> data, Army army) {
    for (List<String> line : data) {
      String unitName = line.get(0);
      int level = Integer.parseInt(line.get(1));
      setUnitType(unitList, unitName, level, army);
    }
  }

  public static Army searchForArmy(String cityName, ArrayList<Army> armies) {
    return armies.stream().filter(army -> army.getCurrentLocation().equals(cityName)).findFirst().orElse(null);
  }

  public static City searchForCity(String cityName, ArrayList<City> availableCities) throws NullPointerException {
    return availableCities.stream().filter(city -> cityName.equals(city.getName())).findFirst().orElse(null);
  }

  private void setUnitType(ArrayList<Unit> unitList, String unitName, int level, Army army) {
    if (unitName.equals("Archer")) {
      Unit archer = new Archer(level);
      addUnitToUnits(unitList, army, archer);
    } else if (unitName.equals("Infantry")) {
      Unit infantry = new Infantry(level);
      addUnitToUnits(unitList, army, infantry);
    } else {
      Unit cavalry = new Cavalry(level);
      addUnitToUnits(unitList, army, cavalry);
    }
  }

  private void addUnitToUnits(ArrayList<Unit> unitList, Army army, Unit archer) {
    archer.setParentArmy(army);
    unitList.add(archer);
  }

  public int searchForDistance(String x, String y) {
    for (Distance distance : distances) {
      if (distance.containsCity(x, y)) {
        return distance.getDistance();
      }
    }
    return 0;
  }

  public void targetCity(Army army, String targetName) {
    if (army.getCurrentLocation().equals(targetName)) {
      return;
    }
    String currentCity = army.getCurrentLocation();
    City previousCity = searchForCity(army.getCurrentLocation(), availableCities);
    int distance = searchForDistance(currentCity, targetName);

    if (army.getCurrentLocation().equals(ON_ROAD)) {
      currentCity = army.getTarget();
      distance = searchForDistance(currentCity, targetName);
      distance += army.getDistancetoTarget();
    }

    if (army.getCurrentStatus().equals(Status.BESIEGING)) {
      previousCity.removeSieging();
    }

    army.setDistancetoTarget(distance);
    army.setTarget(targetName);
    army.setCurrentLocation(ON_ROAD);
    army.setCurrentStatus(Status.MARCHING);
    if (gameListener != null) {
      gameListener.onTargetCity(army, previousCity);
    }
  }

  public void endTurn() {
    currentTurnCount++;
    player.setCurrentSiege(0);
    clearBuildings();
    handleTarget();
    feedArmy();
    updateSiege();
    whoWon();
  }

  private void updateSiege() {
    for (City city : availableCities) {
      if (city.isUnderSiege()) {
        Army army = searchForArmy(city.getName(), player.getControlledArmies());
        if (city.reachedMaxSiege()) {
          forceAttack(city, army);
        } else {
          attackerLoseUnits(city, army);
        }
      }
    }
  }

  private void attackerLoseUnits(City city, Army army) {
    city.getDefendingArmy().killUnits();
    city.incTurnsUnderSiege();
    if (gameListener != null) {
      gameListener.onSiegeUpdate(city, army);
    }
  }

  private void forceAttack(City city, Army army) {
    city.setUnderSiege(false);
    if (gameListener != null) {
      gameListener.attackY3am(city, army);
    }
  }

  private void handleTarget() {
    for (Army army : player.getControlledArmies()) {
      if (!army.isReached()) {
        army.decTargetDistance();
        if (gameListener != null) {
          gameListener.onDistanceUpdated(army);
        }
        if (army.getDistancetoTarget() == 0) {
          army.setArmyArrived();
          if (gameListener != null) {
            gameListener.armyArrived(army);
          }
        }
      }
    }
  }

  private void feedArmy() {
    double foodNeeded = 0;
    foodNeeded += player.attackingArmyFeeding(foodNeeded);
    foodNeeded += player.defendingArmyFeeding(foodNeeded);
    System.out.println(foodNeeded);
    if (!player.isFoodEnough(foodNeeded)) {
      player.loseAttackingArmies();
      player.loseDefendingArmies();
    }
    player.setFood(player.getFood() - foodNeeded);
    if (gameListener != null) {
      gameListener.onFeedUpdated();
    }
  }

  private void clearBuildings() {
    for (City city : player.getControlledCities()) {
      city.clearMilitaryBuildings();
      for (EconomicBuilding economicBuilding : city.getEconomicalBuildings()) {
        economicBuilding.setCoolDown(false);
        player.getHarvestAndTreasury(economicBuilding);
      }
    }
    if (gameListener != null) {
      gameListener.onTreasuryUpdate();
    }
  }

  public void occupy(Army army, String cityName) {
    City city = searchForCity(cityName, availableCities);
    player.getControlledArmies().remove(army);
    player.addControlCity(city);
    city.setDefendingArmy(army);
    city.removeSieging();
    army.setCurrentStatus(Status.IDLE);
    if (gameListener != null) {
      gameListener.onOccupy(city, army);
    }
  }

  public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
    if (player.isFriend(attacker) && player.isFriend(defender)) {
      throw new FriendlyFireException();
    }
    boolean attackerTurn = true;
    while (theBattleIsGoing(attacker, defender)) {
      attackerTurn = alternateAttacking(attacker, defender, attackerTurn);
    }
   battleEnded(attacker, defender);
  }

  private boolean alternateAttacking(Army attacker, Army defender, boolean attackerTurn) throws FriendlyFireException {
    Unit attackerUnit = attacker.getRandomUnit();
    Unit defenderUnit = defender.getRandomUnit();
    if (attackerTurn) {
      attackerUnit.attack(defenderUnit);
    } else {
      defenderUnit.attack(attackerUnit);
    }
    attackerTurn = !attackerTurn;
    return attackerTurn;
  }

  private boolean theBattleIsGoing(Army attacker, Army defender) {
    return attacker.didWinTheBattle() && defender.didWinTheBattle();
  }

  private void removeTheAttack(Army attacker, Army defender) {
    player.getControlledArmies().remove(attacker);
    City currentCity = searchForCity(defender.getCurrentLocation(), availableCities);
    currentCity.removeSieging();
   
  }

  public void battleEnded(Army attacker, Army defender) {
    if (attacker.didWinTheBattle()) {
      occupy(attacker, defender.getCurrentLocation());

      if (gameListener != null) {
        gameListener.OnBattleEnded(attacker, defender, true);
      }
    } else if (defender.didWinTheBattle()) {
      removeTheAttack(attacker, defender);
      gameListener.OnBattleEnded(attacker, defender, false);
    }
  }

  public void whoWon() {
    if (isGameOver()) {
      if (isThePlayerWon()) {
        if (gameListener != null) {
          gameListener.playerWon();
        }
      } else {
        if (gameListener != null) {
          gameListener.playerLost();
        }
      }
    }
  }

  public boolean isGameOver() {
    return isThePlayerWon() || isTurnsOver();
  }

  private boolean isTurnsOver() {
    return currentTurnCount > maxTurnCount;
  }

  private boolean isThePlayerWon() {
    return availableCities.size() == player.getControlledCities().size();
  }

  public void startBattle(Army army, City city) throws FriendlyFireException, TargetNotReachedException {
    if (army.haveReached(city)) {
      throw new TargetNotReachedException("the army hasn't arrived yet!");
    }

    if (player.isFriend(city)) {
      System.out.println(player.getControlledCities().size());
      System.out.println(city.getName());
      throw new FriendlyFireException("you can't attack a friend");
    }

  }

  public String toString(Army army) {
    String r = "Current location : " + army.getCurrentLocation() + "\n" + "Current status : " + army.getCurrentStatus()
        + "\n" + NUMBER_OF_UNITS + army.getUnits().size() + "\n";
    if (army.getCurrentStatus().equals(Status.MARCHING))
      r += "Target : " + army.getTarget() + "\n" + "No of turns till reach : " + army.getDistancetoTarget() + "\n"
          + "\n";
    if (army.getCurrentStatus().equals(Status.BESIEGING))
      r += "Besieged city : " + army.getCurrentLocation() + "\n" + "Turns under siege : "
          + searchForCity(army.getCurrentLocation(), this.getAvailableCities()).getTurnsUnderSiege() + "\n" + "\n";
    return r;
  }
}
