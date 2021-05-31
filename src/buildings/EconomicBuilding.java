package buildings;

public abstract class EconomicBuilding extends Building {

  public EconomicBuilding() {

  }

  public EconomicBuilding(int cost, int upgradeCost) {
    super(cost, upgradeCost);
  }

  public abstract int harvest();
}
