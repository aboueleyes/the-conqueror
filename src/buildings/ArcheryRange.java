package buildings;

public class ArcheryRange extends MilitaryBuilding {

  private static final int ARCHERY_RANGE_COST = 1500;
  private static final int ARCHERY_RANGE_UPGRADE_COST = 800;
  private static final int ARCHERY_RANGE_RECRUITMENT_COST = 400;

  public ArcheryRange() {
    super(ARCHERY_RANGE_COST, ARCHERY_RANGE_UPGRADE_COST, ARCHERY_RANGE_RECRUITMENT_COST);
  }
}
