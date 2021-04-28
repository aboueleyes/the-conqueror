package units;

public abstract class Unit {

  private int level; // The current level of a unit. READ ONLY.
  private int maxSoldierCount; // The maximum number of soldiers a unit can  hold
  private int currentSoldierCount; // the current number of soldiers inside a unit 
  private double idleUpkeep;   // the amount of food a unit consume when being idle
  private double marchingUpkeep; // the amount of food a unit consume when marching to another city
  private double siegeUpkeep; // the amount of food a unit consume when laying siege

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
    this.currentSoldierCount = currentSoldierCount;
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
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  
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
  }
}
