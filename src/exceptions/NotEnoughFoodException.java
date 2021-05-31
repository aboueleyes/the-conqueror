package exceptions;

public class NotEnoughFoodException extends BuildingException {

  public NotEnoughFoodException() {
    super();
  }

  public NotEnoughFoodException(String s) {
    super(s);
  }
}
