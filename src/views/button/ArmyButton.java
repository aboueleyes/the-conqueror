package views.button;

import java.awt.FontFormatException;
import java.io.IOException;
import java.awt.BorderLayout;
import units.Army;

public class ArmyButton  extends StyledButton{
  private Army army;
  public ArmyButton(String string,int size) throws FontFormatException, IOException {
    super(string,size);
    
  }
  public Army getArmy() {
    return army;
  }
  public void setArmy(Army army) {
    this.army = army;
  }

    
}
