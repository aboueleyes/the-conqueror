package exceptions;

public abstract class ArmyException extends EmpireException {
  protected ArmyException() {
    super();
  }

  protected ArmyException(String s) {
    super(s);
  }
}
