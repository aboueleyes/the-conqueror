package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

  private static final int ARCHERY_RANGE_COST = 1500;
  private static final int[] ARCHERY_RANGE_UPGRADE_COST = { 800, 700 };
  private static final int[] ARCHERY_RANGE_RECRUITMENT_COST = { 400, 450, 500 };

  public ArcheryRange() {
    super(ARCHERY_RANGE_COST, ARCHERY_RANGE_UPGRADE_COST[0], ARCHERY_RANGE_RECRUITMENT_COST[0]);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    super.upgrade();
    if (this.getLevel() < 3)
      setUpgradeCost(ARCHERY_RANGE_UPGRADE_COST[getLevel() - 1]);
    setRecruitmentCost(ARCHERY_RANGE_RECRUITMENT_COST[getLevel() - 1]);
  }

  @Override
  public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
    if (isCoolDown()) {
      throw new BuildingInCoolDownException("Building is cooling down");
    }

    else if (getCurrentRecruit() == getMaxRecruit()) {
      throw new MaxRecruitedException("You have reached the max recruit");
    } else {
      setCurrentRecruit(getCurrentRecruit() + 1);
      return new Archer(getLevel());
    }
  }
}
