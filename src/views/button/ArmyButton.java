package views.button;

import engine.City;
import units.Army;

public class ArmyButton extends StyledButton {
  private Army army;
  private City city;

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public ArmyButton(String string, int size) {
    super(string, size);

  }

  public Army getArmy() {
    return army;
  }

  public void setArmy(Army army) {
    this.army = army;
  }

}
