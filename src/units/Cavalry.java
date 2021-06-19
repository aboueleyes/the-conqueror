package units;

public class Cavalry extends Unit {

  private static final double[][] CAVALRY_VAlUES = { { 40, 0.6, 0.7, 0.75 }, { 40, 0.6, 0.7, 0.75 },
      { 60, 0.7, 0.8, 0.9 } };
  private static final double[] ARCHER_TARGET_FACTORS = { 0.5, 0.6, 0.7 };
  private static final double[] INFANTRY_TARGET_FACTORS = { 0.3, 0.4, 0.5 };
  private static final double[] CAVALRY_TARGET_FACTORS = { 0.2, 0.2, 0.3 };

  public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
    super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
  }

  public Cavalry(int level) {
    this(level, (int) CAVALRY_VAlUES[level - 1][0], CAVALRY_VAlUES[level - 1][1], CAVALRY_VAlUES[level - 1][2],
        CAVALRY_VAlUES[level - 1][3]);
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
  
  public String toString() {
  	return "type : Cavalry " + "\n" + super.toString();
  }
}
