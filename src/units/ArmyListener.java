package units;

public interface ArmyListener {
  public void onKill(Army army);

  public void onRelocate(Army army, Unit unit);
}