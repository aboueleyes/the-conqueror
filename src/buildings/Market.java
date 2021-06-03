package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

  private static final int MARKET_COST = 1500;
  private static final int[] MARKET_UPGRADE_COST = { 700, 1000 };
  private static final int[] HARVEST_VALUES = { 1000, 1500, 2000 };

  public Market() {
    super(MARKET_COST, MARKET_UPGRADE_COST[0]);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    super.upgrade();
    if(this.getLevel() < 3)
    	setUpgradeCost(MARKET_UPGRADE_COST[getLevel() - 1]);
  }

  @Override
  public int harvest() {
    return HARVEST_VALUES[getLevel() - 1];
  }
}
