package engine;

import buildings.Building;
import units.Army;
import units.Unit;

public interface PlayerListener {
  public void onBuild(Building building, City city, String type);

  public void onTreasuryUpdate();

  public void unitRecruited(Unit unit, City city);

  public void buildingUpgraded(Building building, City city);

  public void onInitiated(City city, Unit unit);

  public void onSiegeing(Army army, City city);
}