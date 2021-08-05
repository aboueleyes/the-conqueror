package units;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.City;
import exceptions.MaxCapacityException;
import views.panel.ArmyPanel;
import views.panel.StationaryArmyPanel;

public class Army {

  private Status currentStatus = Status.IDLE;
  private List<Unit> units;
  private int distancetoTarget = -1;
  private String target = "";
  private String currentLocation;
  private static final int MAX_TO_HOLD = 10;
  private ArmyPanel armyPanel;
  private StationaryArmyPanel stationaryArmyPanel;
  private ArmyListener armyListener;

  public ArmyPanel getArmyPanel() {
    return armyPanel;
  }

  public StationaryArmyPanel getStationaryArmyPanel() {
    return stationaryArmyPanel;
  }

  public void setStationaryArmyPanel(StationaryArmyPanel stationaryArmyPanel) {
    this.stationaryArmyPanel = stationaryArmyPanel;
  }

  public void setArmyPanel(ArmyPanel armyPanel) {
    this.armyPanel = armyPanel;
  }

  public ArmyListener getArmyListener() {
    return armyListener;
  }

  public void setArmyListener(ArmyListener armyListener) {
    this.armyListener = armyListener;
  }

  public Status getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(Status currentStatus) {
    this.currentStatus = currentStatus;
  }

  public List<Unit> getUnits() {
    return units;
  }

  public void setUnits(List<Unit> units) {
    this.units = units;
  }

  public int getDistancetoTarget() {
    return distancetoTarget;
  }

  public void setDistancetoTarget(int distancetoTarget) {
    this.distancetoTarget = distancetoTarget;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getCurrentLocation() {
    return currentLocation;
  }

  public void decTargetDistance() {
    distancetoTarget--;
  }

  public void setCurrentLocation(String currentLocation) {
    this.currentLocation = currentLocation;
  }

  public Army(String currentLocation) {
    this.currentLocation = currentLocation;
    units = new ArrayList<>();
  }

  public void relocateUnit(Unit unit) throws MaxCapacityException {
    if (this.getUnits().size() == MAX_TO_HOLD) {
      throw new MaxCapacityException("You have reached the max capacity");
    }
    this.getUnits().add(unit);
    unit.getParentArmy().getUnits().remove(unit);
    unit.setParentArmy(this);
    if (armyListener != null) {
      armyListener.onRelocate(this, unit);
    }
  }

  public void handleAttackedUnit(Unit unit) {
    if (unit.getCurrentSoldierCount() != 0) {
      return;
    }
    this.getUnits().remove(unit);
    if (armyListener != null) {
      armyListener.onRemovedUnit(this, unit);
    }
  }

  public double foodNeeded() {
    double foodNeeded = 0;
    for (Unit unit : this.getUnits()) {
      foodNeeded += calcluateFood(unit);
    }
    return foodNeeded;
  }

  private double calcluateFood(Unit unit) {
    if (currentStatus.equals(Status.IDLE)) {
      return unit.getCurrentSoldierCount() * unit.getIdleUpkeep();
    }
    if (currentStatus.equals(Status.BESIEGING)) {
      return unit.getCurrentSoldierCount() * unit.getSiegeUpkeep();
    }
    return unit.getCurrentSoldierCount() * unit.getMarchingUpkeep();
  }

  public void killUnits() {
    for (Unit unit : units) {
      unit.decCurrentSoldierCount();
    }

  }

  public boolean isReached() {
    return target.equals("");
  }

  public Unit getRandomUnit() {
    /** source https://stackoverflow.com/a/35471979/9260982 */
    return units.get(new Random().nextInt(units.size()));
  }

  public void setArmyArrived() {
    currentLocation = target;
    target = "";
    currentStatus = Status.IDLE;
  }

  public boolean haveReached(City city) {
    return !currentLocation.equals(city.getName());
  }

  public boolean didWinTheBattle() {
    return !units.isEmpty();
  }
}
