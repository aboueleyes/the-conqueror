package units;

public interface ArmyListener {
 
  public void onRemovedUnit(Army army,Unit unit);
  public void onRelocate(Army army, Unit unit);
}