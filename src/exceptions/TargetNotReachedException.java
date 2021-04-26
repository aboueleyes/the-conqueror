package exceptions;

public class TargetNotReachedException extends ArmyException {

  public TargetNotReachedException() {
    super();
  }

  public TargetNotReachedException(String s) {
    super(s);
  }
}