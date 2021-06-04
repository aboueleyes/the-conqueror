package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

  private static final int BARRACKS_COST = 2000;
  private static final int[] BARRACKS_UPGRADE_COST = { 1000, 1500, 0 };
  private static final int [] BARRACKS_RECRUITMENT_COST = { 500, 550, 600 };

  public Barracks() {
    super(BARRACKS_COST, BARRACKS_UPGRADE_COST[0], BARRACKS_RECRUITMENT_COST[0]);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    super.upgrade();
    if (getLevel()<3)
     setUpgradeCost(BARRACKS_UPGRADE_COST[getLevel() - 1]);
    setRecruitmentCost(BARRACKS_RECRUITMENT_COST[getLevel() - 1]);
  }

  @Override
  public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
    if (isCoolDown()) {
      throw new BuildingInCoolDownException();
    }
    if (getCurrentRecruit() == getMaxRecruit()) {
      throw new MaxRecruitedException();
    }
    setCurrentRecruit(getCurrentRecruit() + 1);
    return new Infantry(getLevel());

  }
}
