package buildings;

public class BuildingFactory {
    public Building createBuilding(String buildingType) {
        if (buildingType.equals("Farm")) {
            return new Farm();
        }
        if (buildingType.equals("Market")) {
            return new Market();
        }
        if (buildingType.equals("Stable") || buildingType.equals("Cavalry")) {
            return new Stable();
        }
        if (buildingType.equals("Barracks") || buildingType.equals("Infantry")) {
            return new Barracks();
        }
        if (buildingType.equals("ArcheryRange") || buildingType.equals("Archer")) {
            return new ArcheryRange();
        }
        return null;
    }

}
