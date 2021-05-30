package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

  private static final int ARCHERY_RANGE_COST = 1500;
  private static final int ARCHERY_RANGE_UPGRADE_COST = 800;
  private static final int ARCHERY_RANGE_RECRUITMENT_COST = 400;

  public ArcheryRange() {
    super(ARCHERY_RANGE_COST, ARCHERY_RANGE_UPGRADE_COST, ARCHERY_RANGE_RECRUITMENT_COST);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
     if(isCoolDown()) {
       throw new BuildingInCoolDownException();
     }

     int archeryRangeLevel = getLevel();
     switch(archeryRangeLevel){
       case 1 : setLevel(2); setUpgradeCost(700); setRecruitmentCost(450); break;
       case 2 : setLevel(3); setRecruitmentCost(500); break;
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
      return new Archer(getLevel());
    }
  }
}
