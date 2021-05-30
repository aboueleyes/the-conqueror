package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

  private static final int BARRACKS_COST = 2000;
  private static final int BARRACKS_UPGRADE_COST = 1000;
  private static final int BARRACKS_RECRUITMENT_COST = 500;

  public Barracks() {
    super(BARRACKS_COST, BARRACKS_UPGRADE_COST, BARRACKS_RECRUITMENT_COST);
  }

  @Override
  public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
   if(isCoolDown()) {
       throw new BuildingInCoolDownException();
     }

    int barracksLevel = getLevel();
    switch(barracksLevel){
      case 1 : setLevel(2); setUpgradeCost(1500); setRecruitmentCost(550); break;
      case 2 : setLevel(3); setRecruitmentCost(600); break;
      default : throw new MaxLevelException();
    }
    setCoolDown(true);
    
  }

  @Override
  public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
    if(isCoolDown()){
      throw new BuildingInCoolDownException();
    }
    if(getCurrentRecruit()==getMaxRecruit()){
      throw new MaxRecruitedException();
    }
    setCurrentRecruit(getCurrentRecruit()+1);
    return new Infantry(getLevel());
    
  }
}
