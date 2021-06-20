package views.button;

import java.awt.FontFormatException;
import java.io.IOException;

import engine.City;

import java.awt.BorderLayout;
import units.Army;
import units.Unit;

public class UnitButton extends StyledButton {
  private Unit unit;
  private City city;

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public UnitButton(String string, int size) {
    super(string, size);

  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

}
