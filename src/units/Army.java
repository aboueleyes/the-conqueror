package units;

import java.util.ArrayList;
import java.util.List;

public class Army {

  private Status currentStatus = Status.IDLE;
  private List<Unit> units;
  private int distancetoTarget = -1;
  private String target = "";
  private String currentLocation;
  private final int maxToHold = 10; // should better declared as static final

  public int getMaxToHold() {
    return maxToHold;
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

  public void setCurrentLocation(String currentLocation) {
    this.currentLocation = currentLocation;
  }

  public Army(String currentLocation){
    this.currentLocation = currentLocation;
    units = new ArrayList<>();
  }
}
