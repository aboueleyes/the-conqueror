package units;

import java.util.ArrayList;
import java.util.Random;

import exceptions.MaxCapacityException;

public class Army {

  private Status currentStatus = Status.IDLE; // the current status of an army initially being idle .READ ONLY
  private ArrayList<Unit> units; // a list containing the units of an army
  private int distancetoTarget = -1; // the distance needed to reach target city
  private String target = ""; // the target city
  private String currentLocation; // the current location of the army either in a city or road to another
  private final int maxToHold = 10; // maximum number of units an army could hold (should better declared as static
                                    // final)

  public int getMaxToHold() {
    return maxToHold;
  }

  public Status getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(Status currentStatus) {
    this.currentStatus = currentStatus;
  }

  public ArrayList<Unit> getUnits() {
    return units;
  }

  public void setUnits(ArrayList<Unit> units) {
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
  
  public void decTargetDistance(){
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
    if (this.getUnits().size() == this.getMaxToHold())
      throw new MaxCapacityException();
    this.getUnits().add(unit);
    unit.getParentArmy().getUnits().remove(unit);
    unit.setParentArmy(this);
  }

  public void handleAttackedUnit(Unit u) {
    if (u.getCurrentSoldierCount() == 0)
      this.getUnits().remove(u);
  }

  public double foodNeeded() {
    double foodNeeded = 0;
    for (Unit unit : this.getUnits()) {
      if (this.getCurrentStatus().equals(Status.IDLE)) {
        foodNeeded += unit.getCurrentSoldierCount() * unit.getIdleUpkeep();
      } else if (this.getCurrentStatus().equals(Status.BESIEGING)) {
        foodNeeded += unit.getCurrentSoldierCount() * unit.getSiegeUpkeep();
      } else {
        foodNeeded += unit.getCurrentSoldierCount() * unit.getMarchingUpkeep();
      }
    }
    return foodNeeded;
  }

  public void killUnits() {
    for (Unit unit : units) {
      unit.decCurrentSoldierCount();
    }
  }
  public  Unit getRandomUnit() {
    /** source https://stackoverflow.com/a/35471979/9260982 */
    return units.get(new Random().nextInt(units.size()));
  }
}
