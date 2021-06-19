package engine;

import units.Army;

public interface GameListener {

  public void onTargetCity(Army army, City city);

  public void onSiegeUpdate(City city, Army army);
}
