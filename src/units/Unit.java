package units;

import exceptions.FriendlyFireException;
import views.panel.DefendingUnitPanel;
import views.panel.InfoUnitPanel;
import views.panel.UnitPanel;
import java.awt.event.*;

public abstract class Unit {

  private int level;
  private int maxSoldierCount;
  private int currentSoldierCount;
  private double idleUpkeep;
  private double marchingUpkeep;
  private double siegeUpkeep;
  private Army parentArmy;
  private DefendingUnitPanel unitPanel;
  private UnitPanel battleUnitPanel;
  private InfoUnitPanel infoUnitPanel;
  UnitListener unitListener;

  public UnitListener getUnitListener() {
    return unitListener;
  }

  public InfoUnitPanel getInfoUnitPanel() {
    return infoUnitPanel;
  }

  public void setInfoUnitPanel(InfoUnitPanel infoUnitPanel) {
    this.infoUnitPanel = infoUnitPanel;
  }

  public void setUnitListener(UnitListener unitListener) {
    this.unitListener = unitListener;
  }

  public Army getParentArmy() {
    return parentArmy;
  }

  public UnitPanel getBattleUnitPanel() {
    return battleUnitPanel;
  }

  public void setBattleUnitPanel(UnitPanel battleUnitPanel) {
    this.battleUnitPanel = battleUnitPanel;
  }

  public DefendingUnitPanel getUnitPanel() {
    return unitPanel;
  }

  public void setUnitPanel(DefendingUnitPanel unitPanel) {
    this.unitPanel = unitPanel;
  }

  public void setParentArmy(Army parentArmy) {
    this.parentArmy = parentArmy;
  }

  public int getLevel() {
    return level;
  }

  public int getMaxSoldierCount() {
    return maxSoldierCount;
  }

  public int getCurrentSoldierCount() {
    return currentSoldierCount;
  }

  public void setCurrentSoldierCount(int currentSoldierCount) {
    if (currentSoldierCount < 0) {
      currentSoldierCount = 0;
    }
    this.currentSoldierCount = currentSoldierCount;
  }

  public void decCurrentSoldierCount() {
    setCurrentSoldierCount(currentSoldierCount - (int) (currentSoldierCount * 0.1));
    if (unitListener != null) {
      unitListener.onUpdateSoldierCount(this);
    }
  }

  public double getIdleUpkeep() {
    return idleUpkeep;
  }

  public double getMarchingUpkeep() {
    return marchingUpkeep;
  }

  public double getSiegeUpkeep() {
    return siegeUpkeep;
  }

  @Override
  public String toString() {
    return ("Level : " + this.getLevel() + "\n" + "Current Soldier Count : " + this.getCurrentSoldierCount() + "\n"
        + "Max Soldier Count : " + this.getMaxSoldierCount() + "\n");
  }

  protected Unit() {

  }

  protected Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
    this.level = level;
    this.maxSoldierCount = maxSoldierCount;
    this.idleUpkeep = idleUpkeep;
    this.marchingUpkeep = marchingUpkeep;
    this.siegeUpkeep = siegeUpkeep;
    this.currentSoldierCount = maxSoldierCount;
  }

  public abstract double unitFactor(Unit target, int level);

  public void attack(Unit target) throws FriendlyFireException {
    if (this.getParentArmy().equals(target.getParentArmy())) {
      throw new FriendlyFireException("You cannot attack a friendly unit");
    } else {
      int currentSoldierBeforeAttack = target.currentSoldierCount;
      target.setCurrentSoldierCount(
          target.currentSoldierCount - (int) (this.currentSoldierCount * this.unitFactor(target, level)));
      target.getParentArmy().handleAttackedUnit(target);
      if (unitListener != null) {
        unitListener.unitOnattack(this, target, currentSoldierBeforeAttack - target.getCurrentSoldierCount());
      }
    }
  }

  public void addInfoUnitPanel(ActionListener a) {
    InfoUnitPanel infoUnitPanel = new InfoUnitPanel(a, this);
    setInfoUnitPanel(infoUnitPanel);
  }

  public String getType() {
    return this.getClass().getName().substring(6);
  }

  public String getCurrentLocation() {
    return parentArmy.getCurrentLocation();
  }

  public void enableSelect() {
    battleUnitPanel.getSelect().setEnabled(true);
  }
}
