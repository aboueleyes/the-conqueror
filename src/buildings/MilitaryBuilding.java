package buildings;

public abstract class MilitaryBuilding extends Building {

  private int recruitmentCost; // The cost for recruiting a unit.
  private int currentRecruit; // Current number of units recruited by a building inside a turn

  /**
   * This constant should be static final one. no point of doing getter and
   * encupclation it also should follow constant conventin naming MAX_RECRUIT; but
   * we have no choice ;(
   */
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

  /**
   * This also should be protected since no point of public constrctor
   */
  public MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost) {
    super(cost, upgradeCost);
    this.recruitmentCost = recruitmentCost;
  }
}
