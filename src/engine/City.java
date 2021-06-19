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
import units.*;

public class City {

  private String name; // The name of the city, READ ONLY
  private ArrayList<EconomicBuilding> economicalBuildings; // ArrayList containing the economical building in the
                                                           // city,READ
  private ArrayList<MilitaryBuilding> militaryBuildings; // ArrayList containing the military building in the city, READ
  private Army defendingArmy; // The defending army of the city
  private int turnsUnderSiege = -1; // Number of turns the city has been siege
  private boolean underSiege = false; // Variable checking if the is under siege or not

  public String getName() {
    return this.name;
  }

  public ArrayList<EconomicBuilding> getEconomicalBuildings() {
    return this.economicalBuildings;
  }

  public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
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
    for (EconomicBuilding economicBuilding : economicalBuildings) {
      if (type.equals("Farm") && economicBuilding instanceof Farm) {
        return economicBuilding;
      }
      if (type.equals("Market") && economicBuilding instanceof Market) {
        return economicBuilding;
      }
    }
    for (MilitaryBuilding militaryBuilding : militaryBuildings) {
      if (type.equals("Stable") && militaryBuilding instanceof Stable) {
        return militaryBuilding;
      }
      if (type.equals("Barracks") && militaryBuilding instanceof Barracks) {
        return militaryBuilding;
      }
      if (type.equals("ArcheryRange") && militaryBuilding instanceof ArcheryRange) {
        return militaryBuilding;
      }
    }
    return null;
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
}
