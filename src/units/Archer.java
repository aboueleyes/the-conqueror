package units;

public class Archer extends Unit {

  private static final double[][] ARCHER_VAlUES = { { 60, 0.4, 0.5, 0.6 }, { 60, 0.4, 0.5, 0.6 },
      { 70, 0.5, 0.6, 0.7 } };
  private static final double[] ARCHER_TARGET_FACTORS = { 0.3, 0.4, 0.5 };
  private static final double[] INFANTRY_TARGET_FACTORS = { 0.2, 0.3, 0.4 };
  private static final double[] CAVALRY_TARGET_FACTORS = { 0.1, 0.1, 0.2 };

  public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
    super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
  }

  public Archer(int level) {
    this(level, (int) ARCHER_VAlUES[level - 1][0], ARCHER_VAlUES[level - 1][1], ARCHER_VAlUES[level - 1][2],
        ARCHER_VAlUES[level - 1][3]);
  }

  @Override
  public double unitFactor(Unit target, int level) {
    if (target instanceof Archer) {
      return ARCHER_TARGET_FACTORS[level - 1];
    }
    if (target instanceof Infantry) {
      return INFANTRY_TARGET_FACTORS[level - 1];
    }
    return CAVALRY_TARGET_FACTORS[level - 1];
  }
}
