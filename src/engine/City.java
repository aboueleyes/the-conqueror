package engine;

import java.util.ArrayList;
import java.util.List;

import buildings.Building;
import buildings.BuildingFactory;
import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

public class City {

  private String name;
  private ArrayList<EconomicBuilding> economicalBuildings;
  private ArrayList<MilitaryBuilding> militaryBuildings;
  private Army defendingArmy;
  private int turnsUnderSiege = -1;
  private boolean underSiege = false;

  private static final int MAX_TURNS_UNDER_SEIGE = 3;

  public String getName() {
    return this.name;
  }

  public List<EconomicBuilding> getEconomicalBuildings() {
    return this.economicalBuildings;
  }

  public List<MilitaryBuilding> getMilitaryBuildings() {
    return this.militaryBuildings;
  }

  public Army getDefendingArmy() {
    return this.defendingArmy;
  }

  public void setDefendingArmy(Army defendingArmy) {
    this.defendingArmy = defendingArmy;
  }

  public int getTurnsUnderSiege() {
    return this.turnsUnderSiege;
  }

  public void setTurnsUnderSiege(int turnsUnderSiege) {
    this.turnsUnderSiege = turnsUnderSiege;
  }

  public void incTurnsUnderSiege() {
    turnsUnderSiege++;
  }

  public boolean isUnderSiege() {
    return this.underSiege;
  }

  public void setUnderSiege(boolean underSiege) {
    this.underSiege = underSiege;
  }

  public City(String name) {
    this.name = name;
    economicalBuildings = new ArrayList<>();
    militaryBuildings = new ArrayList<>();
    defendingArmy = new Army(name);
  }

  public Building searchForBuilding(String type) {
    var factory = new BuildingFactory();
    var building = factory.createBuilding(type);
    Building eBuilding = economicalBuildings.stream().filter(ecobuilding -> ecobuilding.equals(building)).findFirst()
        .orElse(null);
    if (eBuilding != null) {
      return eBuilding;
    }
    return militaryBuildings.stream().filter(mBuilding -> mBuilding.equals(building)).findFirst()
        .orElse((MilitaryBuilding) building);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof City)) {
      return false;
    }
    City city = (City) o;
    return city.getName().equals(this.getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }

  public boolean reachedMaxSiege() {
    return turnsUnderSiege == MAX_TURNS_UNDER_SEIGE;
  }

  public void removeSieging() {
    turnsUnderSiege = -1;
    underSiege = false;
  }

  public double feedDefendingArmy() {
    return defendingArmy.foodNeeded();
  }

  public void clearMilitaryBuildings() {
    militaryBuildings.forEach(MilitaryBuilding::clear);
  }
}
