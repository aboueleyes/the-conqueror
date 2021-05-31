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
import exceptions.BuildingException;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.InvalidBuildingException;
import exceptions.InvalidUnitException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughFoodException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player {

  private String name; // Variable representing the name of the player, READ ONLY
  private ArrayList<City> controlledCities; // An ArrayList containing the players controlled cities, READ ONLY
  private ArrayList<Army> controlledArmies; // An ArrayList containing the players controlled armies, READ ONLY
  private double treasury; // The amount of gold the player has
  private double food; // The amount of food the player has

  public String getName() {
    return this.name;
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
    this.food = food;
  }
  public void decFood(double food) throws NotEnoughFoodException{
    if (food > this.food){
      throw new NotEnoughFoodException(); 
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

  public static MilitaryBuilding searchForBuliding(String type, ArrayList<MilitaryBuilding> militaryBuildings)
      throws InvalidBuildingException {
    for (MilitaryBuilding militaryBuilding : militaryBuildings) {
      if (type.equals("Archer") && militaryBuilding instanceof ArcheryRange)
        return militaryBuilding;
      if (type.equals("Infantry") && militaryBuilding instanceof Barracks)
        return militaryBuilding;
      if (type.equals("Cavalry") && militaryBuilding instanceof Stable)
        return militaryBuilding;
    }
    throw new InvalidBuildingException();
  }

  public void recruitUnit(String type, String cityName)
      throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException, InvalidBuildingException {
    City playerCity = Game.searchForCity(cityName, controlledCities);
    MilitaryBuilding targetBuilding = searchForBuliding(type, playerCity.getMilitaryBuildings());
    if (targetBuilding.getRecruitmentCost() > treasury) {
      throw new NotEnoughGoldException();
    }
    Unit recruitedUnit = targetBuilding.recruit();
    playerCity.getDefendingArmy().getUnits().add(recruitedUnit);
    treasury -= targetBuilding.getRecruitmentCost();
    recruitedUnit.setParentArmy(playerCity.getDefendingArmy());
  }

  public void build(String type, String cityName) throws NotEnoughGoldException {
    // TODO Exception if the bulding already exists
    City playerCity = Game.searchForCity(cityName, controlledCities);
    Building building = null;
    switch (type) {
      case "Farm":
        building = new Farm();
        break;
      case "Market":
        building = new Market();
        break;
      case "Stable":
        building = new Stable();
        break;
      case "Barracks":
        building = new Barracks();
        break;
      default:
        building = new ArcheryRange();
        break;
    }
    if (building.getCost() > treasury) {
      throw new NotEnoughGoldException();
    }
    if (building instanceof MilitaryBuilding) {
      playerCity.getMilitaryBuildings().add((MilitaryBuilding) building);
    } else {
      playerCity.getEconomicalBuildings().add((EconomicBuilding) building);

    }
  }

  public void upgradeBuilding(Building b)
      throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
    int cost = b.getUpgradeCost();
    b.upgrade();
    if (cost > treasury) {
      throw new NotEnoughGoldException();
    }
    treasury -= cost;

  }

  public void initiateArmy(City city, Unit unit) {
    city.getDefendingArmy().getUnits().remove(unit);
    Army attackingArmy = new Army(city.getName());
    attackingArmy.getUnits().add(unit);
    unit.setParentArmy(attackingArmy);
    getControlledArmies().add(attackingArmy);

  }

  public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {

    if (controlledCities.contains(city)){
      throw new FriendlyCityException();
    }
    if (!army.getCurrentLocation().equals(city.getName())) {
      throw new TargetNotReachedException();
    }
    army.setCurrentStatus(Status.BESIEGING);
    city.setUnderSiege(true);

  }
}
