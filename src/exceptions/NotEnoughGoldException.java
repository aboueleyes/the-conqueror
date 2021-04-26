package exceptions;

public class NotEnoughGoldException extends BuildingException {

  public NotEnoughGoldException() {
    super();
  }

  public NotEnoughGoldException(String s) {
    super(s);
  }
}
