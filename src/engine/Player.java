package engine;

import java.util.ArrayList;
import java.util.List;

import buildings.Building;
import buildings.BuildingFactory;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.MilitaryBuilding;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.InvalidBuildingException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.MaxSiegeException;
import exceptions.NotEnoughFoodException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player {

  private static final String NOT_ENOUGH_FOOD = "Not Enough Food";
  private static final String NOT_ENOUGH_GOLD = "Not Enough Gold";
  private String name;
  private ArrayList<City> controlledCities;
  private ArrayList<Army> controlledArmies;
  private double treasury;
  private double food;
  private PlayerListener playerListener;
  private int currentSiege = 0;

  public int getCurrentSiege() {
    return currentSiege;
  }

  public void setCurrentSiege(int currentSiege) {
    this.currentSiege = currentSiege;
  }

  public String getName() {
    return this.name;
  }

  public PlayerListener getPlayerListener() {
    return playerListener;
  }

  public void setPlayerListener(PlayerListener playerListener) {
    this.playerListener = playerListener;
  }

  public List<City> getControlledCities() {
    return this.controlledCities;
  }

  public List<Army> getControlledArmies() {
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
    if (food < 0) {
      food = 0;
    }
    this.food = food;
  }

  public void decFood(double food) throws NotEnoughFoodException {
    if (food > this.food) {
      throw new NotEnoughFoodException(NOT_ENOUGH_FOOD);
    }
    this.food -= food;
  }

  public void addControlCity(City city) {
    controlledCities.add(city);
  }

  public Player(String name) {
    this.name = name;
    controlledCities = new ArrayList<>();
    controlledArmies = new ArrayList<>();
  }

  public static MilitaryBuilding searchForBuilding(String type, List<MilitaryBuilding> list)
      throws InvalidBuildingException {
    BuildingFactory buildingFactory = new BuildingFactory();
    MilitaryBuilding building = (MilitaryBuilding) buildingFactory.createBuilding(type);
    for (MilitaryBuilding militaryBuilding : list) {
      if (building.equals(militaryBuilding))
        return militaryBuilding;
    }
    throw new InvalidBuildingException("Error in CSV files");
  }

  public void recruitUnit(String type, String cityName)
      throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException, InvalidBuildingException {
    var playerCity = Game.searchForCity(cityName, controlledCities);
    if (playerCity == null)
      return;
    MilitaryBuilding targetBuilding;
    try {
      targetBuilding = searchForBuilding(type, playerCity.getMilitaryBuildings());
    } catch (InvalidBuildingException e) {
      return;
    }
    if (targetBuilding.getRecruitmentCost() > treasury) {
      throw new NotEnoughGoldException(NOT_ENOUGH_GOLD);
    }
    var recruitedUnit = targetBuilding.recruit();
    playerCity.getDefendingArmy().getUnits().add(recruitedUnit);
    treasury -= targetBuilding.getRecruitmentCost();
    recruitedUnit.setParentArmy(playerCity.getDefendingArmy());
    if (playerListener != null) {
      playerListener.unitRecruited(recruitedUnit, playerCity);
      playerListener.onTreasuryUpdate();
    }
  }

  private boolean buildingExist(City city, Building building) {
    return (city.getMilitaryBuildings().contains(building)) || (city.getEconomicalBuildings().contains(building));
  }

  public void build(String type, String cityName) throws NotEnoughGoldException {
    var playerCity = Game.searchForCity(cityName, controlledCities);
    if (playerCity == null) {
      return;
    }
    var buildingFactory = new BuildingFactory();
    var building = buildingFactory.createBuilding(type);
    if (buildingExist(playerCity, building)) {
      return;
    }
    if (building.getCost() > treasury) {
      throw new NotEnoughGoldException(NOT_ENOUGH_GOLD);
    }
    treasury -= building.getCost();
    if (building instanceof MilitaryBuilding) {
      playerCity.getMilitaryBuildings().add((MilitaryBuilding) building);
    } else {
      playerCity.getEconomicalBuildings().add((EconomicBuilding) building);

    }
    if (playerListener != null) {
      playerListener.onBuild(building, playerCity, type);
      playerListener.onTreasuryUpdate();
    }
  }

  public void upgradeBuilding(Building building, City city)
      throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
    int cost = building.getUpgradeCost();
    if (cost > treasury) {
      throw new NotEnoughGoldException(NOT_ENOUGH_GOLD);
    }
    building.upgrade();
    treasury -= cost;
    if (playerListener != null) {
      playerListener.buildingUpgraded(building, city);
      playerListener.onTreasuryUpdate();
      playerListener.buildingUpgraded(building, city);
    }
  }

  public void initiateArmy(City city, Unit unit) {
    city.getDefendingArmy().getUnits().remove(unit);
    var attackingArmy = new Army(city.getName());
    attackingArmy.getUnits().add(unit);
    unit.setParentArmy(attackingArmy);
    getControlledArmies().add(attackingArmy);
    if (playerListener != null) {
      playerListener.onInitiated(city, unit, attackingArmy);
    }
  }

  public void laySiege(Army army, City city)
      throws TargetNotReachedException, FriendlyCityException, MaxSiegeException {

    if (controlledCities.contains(city)) {
      throw new FriendlyCityException("You cannot attack a friend city");
    }
    if (!army.getCurrentLocation().equals(city.getName())) {
      throw new TargetNotReachedException("The army hasn't arrived yet");
    }
    if (currentSiege == 1) {
      throw new MaxSiegeException("You reached max siege per turn");
    }
    army.setCurrentStatus(Status.BESIEGING);
    city.setUnderSiege(true);
    city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);
    currentSiege++;
    if (playerListener != null) {
      playerListener.onSiegeing(army, city);
    }
  }

  public boolean isFriend(Army army) {
    return controlledArmies.contains(army);
  }

  public boolean isFriend(City city) {
    return controlledCities.contains(city);
  }

  public void getHarvestAndTreasury(EconomicBuilding economicBuilding) {
    if (economicBuilding instanceof Farm) {
      food = (food + economicBuilding.harvest());
    } else {
      treasury = (treasury + economicBuilding.harvest());
    }
  }

  public boolean isFoodEnough(double foodNeeded) {
    return foodNeeded <= food;
  }

  public void loseDefendingArmies() {
    controlledCities.forEach(city -> city.getDefendingArmy().killUnits());
  }

  public void loseAttackingArmies() {
    controlledArmies.forEach(Army::killUnits);
  }

  public double defendingArmyFeeding(double foodNeeded) {
    for (City city : controlledCities) {
      foodNeeded += city.feedDefendingArmy();
    }
    return foodNeeded;
  }

  public double attackingArmyFeeding(double foodNeeded) {
    for (Army army : controlledArmies) {
      foodNeeded += army.foodNeeded();
    }
    return foodNeeded;
  }

}
