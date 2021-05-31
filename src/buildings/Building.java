package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class Building {

  private int cost; // The cost for creating a building. READ ONLY
  private int level = 1; // The current level of the building.
  private int upgradeCost; // The cost for upgrading buildings level.
  private boolean coolDown = false; // variable stating if the building is cooling down.
  private final int maxLevel = 3;

  public int getCost() {
    return cost;
  }

  public int getMaxLevel() {
    return maxLevel;
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
      throw new BuildingInCoolDownException();
    }
    if (getLevel() == maxLevel) {
      throw new MaxLevelException();
    }
    setLevel(getLevel() + 1);
    setCoolDown(true);
  }
}
