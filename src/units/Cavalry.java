package units;

public class Cavalry  extends Unit{

  private static final double[][] CAVALRY_VAlUES = {
    {40,0.6,0.7,0.75},
    {60,0.6,0.7,0.75},
    {60,0.7,0.8,0.9}
  };
  public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep){
    super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
  }
  public Cavalry(int level){
      this(level, (int)CAVALRY_VAlUES[level -1][0], CAVALRY_VAlUES[level -1][1], CAVALRY_VAlUES[level -1][2],CAVALRY_VAlUES[level -1][3]);
  }
}
