package exceptions;

public abstract class EmpireException extends Exception {
  protected EmpireException() {
    super();
  }

  protected EmpireException(String s) {
    super(s);
  }
}
