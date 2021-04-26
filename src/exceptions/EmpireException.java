package exceptions;

public abstract class EmpireException extends Exception {
  public EmpireException() {
    super();
  }

  public EmpireException(String s) {
    super(s);
  }
}
