package units;

import exceptions.FriendlyFireException;

public abstract class Unit {

  private int level; // The current level of a unit. READ ONLY.
  private int maxSoldierCount; // The maximum number of soldiers a unit can hold
  private int currentSoldierCount; // the current number of soldiers inside a unit
  private double idleUpkeep; // the amount of food a unit consume when being idle
  private double marchingUpkeep; // the amount of food a unit consume when marching to another city
  private double siegeUpkeep; // the amount of food a unit consume when laying siege
  private Army parentArmy;

  public Army getParentArmy() {
    return parentArmy;
  }

  public void setParentArmy(Army parentArmy) {
    this.parentArmy = parentArmy;
  }

  public int getLevel() {
    return level;
  }

  public int getMaxSoldierCount() {
    return maxSoldierCount;
  }

  public int getCurrentSoldierCount() {
    return currentSoldierCount;
  }

  public void setCurrentSoldierCount(int currentSoldierCount) {
    if (currentSoldierCount <0){
      currentSoldierCount = 0;
    }
    this.currentSoldierCount = currentSoldierCount;
  }

  public void decCurrentSoldierCount() {
    setCurrentSoldierCount (currentSoldierCount-(int)(currentSoldierCount * 0.1));
  }

  public double getIdleUpkeep() {
    return idleUpkeep;
  }

  public double getMarchingUpkeep() {
    return marchingUpkeep;
  }

  public double getSiegeUpkeep() {
    return siegeUpkeep;
  }

  @Override
  public String toString() {
    return "Unit [currentSoldierCount=" + currentSoldierCount + ", idleUpkeep=" + idleUpkeep + ", level=" + level
        + ", marchingUpkeep=" + marchingUpkeep + ", maxSoldierCount=" + maxSoldierCount + ", siegeUpkeep=" + siegeUpkeep
        + "]";
  }

  public Unit() {

  }

  public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
    this.level = level;
    this.maxSoldierCount = maxSoldierCount;
    this.idleUpkeep = idleUpkeep;
    this.marchingUpkeep = marchingUpkeep;
    this.siegeUpkeep = siegeUpkeep;
    this.currentSoldierCount = maxSoldierCount;
  }

  public abstract double unitFactor(Unit target, int level);

  public void attack(Unit target) throws FriendlyFireException {
    if (this.getParentArmy().equals(target.getParentArmy())) {
      throw new FriendlyFireException();
    } else {
      target.setCurrentSoldierCount (target.currentSoldierCount- (int)(this.currentSoldierCount * this.unitFactor(target, level)));
      target.getParentArmy().handleAttackedUnit(target);
    }
  }
}