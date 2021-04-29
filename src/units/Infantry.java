package units;

public class Infantry extends Unit {

  private static final double[][] ARCHER_VAlUES = { { 50, 0.5, 0.6, 0.7 }, { 50, 0.5, 0.6, 0.7 },
      { 60, 0.6, 0.7, 0.8 } };

  public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
    super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
  }

  public Infantry(int level) {
    this(level, (int) ARCHER_VAlUES[level - 1][0], ARCHER_VAlUES[level - 1][1], ARCHER_VAlUES[level - 1][2],
        ARCHER_VAlUES[level - 1][3]);
  }
}
