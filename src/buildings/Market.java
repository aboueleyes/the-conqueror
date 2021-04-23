package buildings;

public class Market extends EconomicBuilding {

  private static final int MARKET_COST = 1500;
  private static final int MARKET_UPGRADE_COST = 700;

  public Market() {
    super(MARKET_COST, MARKET_UPGRADE_COST);
  }
}
