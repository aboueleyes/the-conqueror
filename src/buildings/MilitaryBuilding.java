package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.Unit;

public abstract class MilitaryBuilding extends Building {

  private int recruitmentCost; // The cost for recruiting a unit.
  private int currentRecruit; // Current number of units recruited by a building inside a turn

  private final int maxRecruit = 3; // Maximum number of units a building can recruit per turn

  public int getRecruitmentCost() {
    return recruitmentCost;
  }

  public void setRecruitmentCost(int recruitmentCost) {
    this.recruitmentCost = recruitmentCost;
  }

  public int getCurrentRecruit() {
    return currentRecruit;
  }

  public void setCurrentRecruit(int currentRecruit) {
    this.currentRecruit = currentRecruit;
  }

  public int getMaxRecruit() {
    return maxRecruit;
  }

  public MilitaryBuilding() {

  }

  public MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost) {
    super(cost, upgradeCost);
    this.recruitmentCost = recruitmentCost;
  }

  public abstract Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException;

  @Override
  public String toString() {
    return super.toString() + "RecruitmentCost : " + this.getRecruitmentCost() + "\n";
  }

  public void clear() {
    setCoolDown(false);
    currentRecruit = 0;
  }
}
