package buildings;

public class Stable extends MilitaryBuilding {
        
    private static final int STABLE_COST = 2500;
    private static final int STABLE_UPGRADE_COST = 1500;
    private static final int STABLE_RECRUITMENT_COST = 600;

    public Stable() {
        super(STABLE_COST, STABLE_UPGRADE_COST, STABLE_RECRUITMENT_COST);
    }
}
