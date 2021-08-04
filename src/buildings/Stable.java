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
    updateCosts(STABLE_UPGRADE_COST, STABLE_RECRUITMENT_COST);
  }

  @Override
  public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
    if (isCoolDown()) {
      throw new BuildingInCoolDownException("Building is cooling down");
    }

    if (getCurrentRecruit() == getMaxRecruit()) {
      throw new MaxRecruitedException("You have reached the max recruit");
    }

    setCurrentRecruit(getCurrentRecruit() + 1);
    return new Cavalry(getLevel());

  }
}
