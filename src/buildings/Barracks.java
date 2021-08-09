package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

  private static final int BARRACKS_COST = 2000;
  private static final int[] BARRACKS_UPGRADE_COST = { 1000, 1500 };
  private static final int[] BARRACKS_RECRUITMENT_COST = { 500, 550, 600 };

  public Barracks() {
    super(BARRACKS_COST, BARRACKS_UPGRADE_COST[0], BARRACKS_RECRUITMENT_COST[0]);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    super.upgrade();
    updateCosts(BARRACKS_UPGRADE_COST, BARRACKS_RECRUITMENT_COST);
  }

  @Override
  public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
    if (isCoolDown()) {
      throw new BuildingInCoolDownException("Building is cooling down");
    }
    if (isMaxRecruited()) {
      throw new MaxRecruitedException("You have reached the max recruit");
    }
    setCurrentRecruit(getCurrentRecruit() + 1);
    return new Infantry(getLevel());

  }
}
