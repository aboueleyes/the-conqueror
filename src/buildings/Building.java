package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class Building {

  private int cost; // The cost for creating a building. READ ONLY
  private int level = 1; // The current level of the building.
  private int upgradeCost; // The cost for upgrading buildings level.
  private boolean coolDown = true; // variable stating if the building is cooling down.
  private static final int MAX_LEVEL = 3;

  public int getCost() {
    return cost;
  }

  public boolean reachedMaxLevel() {
    return this.getLevel() == MAX_LEVEL;
  }

  public int getMaxLevel() {
    return MAX_LEVEL;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getUpgradeCost() {
    return upgradeCost;
  }

  public void setUpgradeCost(int upgradeCost) {
    this.upgradeCost = upgradeCost;
  }

  public boolean isCoolDown() {
    return coolDown;
  }

  public void setCoolDown(boolean coolDown) {
    this.coolDown = coolDown;
  }

  public Building() {

  }

  public Building(int cost, int upgradeCost) {
    this.cost = cost;
    this.upgradeCost = upgradeCost;
  }

  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    if (isCoolDown()) {
      throw new BuildingInCoolDownException("Building is cooling down");
    }
    if (getLevel() == MAX_LEVEL) {
      throw new MaxLevelException("You have reached the max recruit");
    }
    setLevel(getLevel() + 1);
    setCoolDown(true);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Building)) {
      return false;
    }
    return this.getClass().equals(o.getClass());
  }

  public String toString() {
    return ("Cost : " + this.getCost() + "\n" + "Level : " + this.getLevel() + "\n" + "Upgrade Cost : "
        + this.getUpgradeCost() + "\n");

  }

  public String getType() {
    return this.getClass().getName().substring(10);
  }

}
