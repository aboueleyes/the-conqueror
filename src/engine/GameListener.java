package engine;

import units.Army;

public interface GameListener {

  public void onTargetCity(Army army, City city);

  public void onSiegeUpdate(City city, Army army);

  public void attackY3am(City city, Army army);

  public void armyArrived(Army army);

  public void onDistanceUpdated(Army army);

  public void onFeedUpdated();

  public void onOccupy(City city, Army army);

}
