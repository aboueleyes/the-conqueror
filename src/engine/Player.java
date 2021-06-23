package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.InvalidBuildingException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughFoodException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player {

  private static final String NOT_ENOUGH_FOOD = "Not Enough Food";
  private static final String NOT_ENOUGH_GOLD = "Not Enough Gold";
  private String name; // Variable representing the name of the player, READ ONLY
  private ArrayList<City> controlledCities; // An ArrayList containing the players controlled cities, READ ONLY
  private ArrayList<Army> controlledArmies; // An ArrayList containing the players controlled armies, READ ONLY
  private double treasury; // The amount of gold the player has
  private double food; // The amount of food the player has
  private PlayerListener playerListener;

  public String getName() {
    return this.name;
  }

  public PlayerListener getPlayerListener() {
    return playerListener;
  }

  public void setPlayerListener(PlayerListener playerListener) {
    this.playerListener = playerListener;
  }

  public ArrayList<City> getControlledCities() {
    return this.controlledCities;
  }

  public ArrayList<Army> getControlledArmies() {
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

  public static MilitaryBuilding searchForBuilding(String type, ArrayList<MilitaryBuilding> militaryBuildings)
      throws InvalidBuildingException {
    for (MilitaryBuilding militaryBuilding : militaryBuildings) {
      if (type.equals("Archer") && militaryBuilding instanceof ArcheryRange)
        return militaryBuilding;
      if (type.equals("Infantry") && militaryBuilding instanceof Barracks)
        return militaryBuilding;
      if (type.equals("Cavalry") && militaryBuilding instanceof Stable)
        return militaryBuilding;
    }
    throw new InvalidBuildingException("Error in CSV files");
  }

  public void recruitUnit(String type, String cityName)
      throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException, InvalidBuildingException {
    City playerCity = Game.searchForCity(cityName, controlledCities);
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
    Unit recruitedUnit = targetBuilding.recruit();
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
    City playerCity = Game.searchForCity(cityName, controlledCities);
    if (playerCity == null) {
      return;
    }
    Building building = setBuildingType(type);
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

  private Building setBuildingType(String type) {
    if (type.equals("Farm"))
      return new Farm();
    if (type.equals("Stable"))
      return new Stable();
    if (type.equals("Market"))
      return new Market();
    if (type.equals("Barracks"))
      return new Barracks();
    else
      return new ArcheryRange();
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
    Army attackingArmy = new Army(city.getName());
    attackingArmy.getUnits().add(unit);
    unit.setParentArmy(attackingArmy);
    getControlledArmies().add(attackingArmy);
    if (playerListener != null) {
      playerListener.onInitiated(city, unit, attackingArmy);
    }
  }

  public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {

    if (controlledCities.contains(city)) {
      throw new FriendlyCityException("You cannot attack a friend city");
    }
    if (!army.getCurrentLocation().equals(city.getName())) {
      throw new TargetNotReachedException("The army hasn't arrived yet");
    }
    army.setCurrentStatus(Status.BESIEGING);
    city.setUnderSiege(true);
    city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);

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
