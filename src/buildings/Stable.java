package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {

  private static final int STABLE_COST = 2500;
  private static final int[] STABLE_UPGRADE_COST = { 1500, 2000 };
  private static final int[] STABLE_RECRUITMENT_COST = { 600, 650, 700 };

  public Stable() {
    super(STABLE_COST, STABLE_UPGRADE_COST[0], STABLE_RECRUITMENT_COST[0]);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    super.upgrade();
    if (this.getLevel() < 3)
      setUpgradeCost(STABLE_UPGRADE_COST[getLevel() - 1]);
    setRecruitmentCost(STABLE_RECRUITMENT_COST[getLevel() - 1]);
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
      return new Cavalry(getLevel());
    }
  }
}
