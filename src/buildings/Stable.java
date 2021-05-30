package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {

  private static final int STABLE_COST = 2500;
  private static final int STABLE_UPGRADE_COST = 1500;
  private static final int STABLE_RECRUITMENT_COST = 600;

  public Stable() {
    super(STABLE_COST, STABLE_UPGRADE_COST, STABLE_RECRUITMENT_COST);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
    if(isCoolDown()) {
       throw new BuildingInCoolDownException();
     }

    int stableLevel = getLevel();
    switch(stableLevel){
      case 1 : setLevel(2); setUpgradeCost(2000); setRecruitmentCost(650); break;
      case 2 : setLevel(3); setRecruitmentCost(700); break;
      default : throw new MaxLevelException();
    }
    setCoolDown(true);

  }

  @Override
  public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
    if(isCoolDown()){
      throw new BuildingInCoolDownException();
    }

    else if(getCurrentRecruit()==getMaxRecruit()){
      throw new MaxRecruitedException();
    }
    else{
      setCurrentRecruit(getCurrentRecruit()+1);
      return new Cavalry(getLevel());
    }
  }
}
