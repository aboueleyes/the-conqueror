package units;

public class UnitFactory {
    public Unit createUnit(String name, int level) {
        if (name == null) {
            return null;
        }
        if (name.equals("Cavalry")) {
            return new Cavalry(level);
        }
        if (name.equals("Archer")) {
            return new Archer(level);
        }
        if (name.equals("Infantry")) {
            return new Infantry(level);
        }
        return null;
    }
}
