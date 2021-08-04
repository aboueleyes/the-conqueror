package units;

public interface UnitListener {
  public void unitOnattack(Unit attackerUnit, Unit defenderUnit, int killedSoldiers);

  public void onUpdateSoldierCount(Unit unit);
}
