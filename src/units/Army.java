package units;

import java.util.ArrayList;
import java.util.List;

public class Army {

  private Status currentStatus = Status.IDLE; // the current status of an army initially being idle .READ ONLY
  private List<Unit> units; // a list containing the units of an army
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

  public Army(String currentLocation) {
    this.currentLocation = currentLocation;
    units = new ArrayList<>();
  }
}
