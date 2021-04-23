package buildings;

public class Barracks extends MilitaryBuilding {

  private static final int BARRACKS_COST = 2000;
  private static final int BARRACKS_UPGRADE_COST = 1000;
  private static final int BARRACKS_RECRUITMENT_COST = 500;

  public Barracks() {
    super(BARRACKS_COST, BARRACKS_UPGRADE_COST, BARRACKS_RECRUITMENT_COST);
  }
}
