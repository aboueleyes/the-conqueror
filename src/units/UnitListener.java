package units;

public interface UnitListener {
  public void  UnitOnattack (Unit attackerUnit, Unit DefenderUnit,int killedSoldiers);
  public void OnUpdateSoldierCount (Unit unit);
}
