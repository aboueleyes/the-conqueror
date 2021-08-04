package buildings;

public abstract class EconomicBuilding extends Building {

  protected EconomicBuilding() {

  }

  protected EconomicBuilding(int cost, int upgradeCost) {
    super(cost, upgradeCost);
  }

  public abstract int harvest();

  public void upgradeCost(int[] upgradeCost) {
    if (reachedMaxLevel()) {
      return;
    }
    setUpgradeCost(upgradeCost[getLevel() - 1]);
  }

}
