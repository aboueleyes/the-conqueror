package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

  private static final int FARM_COST = 1000;
  private static final int FARM_UPGRADE_COST = 500;
  private static final int[] HARVEST_VALUES = {500, 700, 1000};
  public Farm() {
    super(FARM_COST, FARM_UPGRADE_COST);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    if(isCoolDown()) {
      throw new BuildingInCoolDownException();
    }
    int farmLevel = getLevel();
    switch(farmLevel){
      case 1 : setUpgradeCost(700); setLevel(2); break;
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
