package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.Unit;

public abstract class MilitaryBuilding extends Building {

  private int recruitmentCost;
  private int currentRecruit;

  private static final int MAX_RECRUIT = 3;

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
    return MAX_RECRUIT;
  }

  protected MilitaryBuilding() {

  }

  protected MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost) {
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

  public void updateCosts(int[] upgradeCosts, int[] recruitCosts) {
    if (!reachedMaxLevel()) {
      setUpgradeCost(upgradeCosts[getLevel() - 1]);
    }
    setRecruitmentCost(recruitCosts[getLevel() - 1]);
  }
}
