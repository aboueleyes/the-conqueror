package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

  private static final int MARKET_COST = 1500;
  private static final int MARKET_UPGRADE_COST = 700;
  private static final int[] HARVEST_VALUES = {1000, 1500, 2000};
  public Market() {
    super(MARKET_COST, MARKET_UPGRADE_COST);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    if(isCoolDown()) {
      throw new BuildingInCoolDownException();
    }

    int marketLevel = getLevel();
    switch(marketLevel){
      case 1 : setUpgradeCost(1000); setLevel(2); break;
      case 2 : setLevel(3); break;
      default : throw new MaxLevelException();
    }
    setCoolDown(true);
  }

   @Override
  public int harvest() {
    return HARVEST_VALUES[getLevel()-1];
  }
}
