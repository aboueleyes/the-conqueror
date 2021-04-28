package units;

public class Archer extends Unit {

  private static final double[][] ARCHER_VAlUES = {
    {60,0.4,0.5,0.6},
    {60,0.4,0.5,0.6},
    {70,0.5,0.6,0.7}
  };

  public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
    super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
  }

  public Archer(int level){
      this(level, (int)ARCHER_VAlUES[level -1][0], ARCHER_VAlUES[level -1][1], ARCHER_VAlUES[level -1][2],ARCHER_VAlUES[level -1][2]);
  }

}
