package buildings;

public class Farm extends EconomicBuilding {
    private static final int FARM_COST = 1000;
    private static final int FARM_UPGRADE_COST = 500;

    public Farm() {
        super(FARM_COST, FARM_UPGRADE_COST);
    }
}
