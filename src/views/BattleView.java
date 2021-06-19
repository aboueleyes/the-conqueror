package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import exceptions.InvalidUnitException;
import units.Unit;
import units.Archer;
import units.Army;
import units.Cavalry;

public class BattleView  extends JFrame{
	
	private PlayerPanel playerPanle;
	private JPanel battleLog;
	private ArmyPanel attackerPanel;
	private ArmyPanel defenderPanel;
	private JPanel centre;
	private Army attackerArmy;
	private Army defenderArmy;
	
	public BattleView(ActionListener a, PlayerPanel playerPanel, Army attackerArmy, Army defenderArmy) {
		super();
		this.playerPanle = playerPanel;
		this.attackerPanel = new ArmyPanel();
		this.defenderPanel = new ArmyPanel();
		this.centre = new JPanel();
		this.attackerArmy = attackerArmy;
		this.defenderArmy = defenderArmy;
		this.battleLog = new JPanel();
		this.setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);
    setVisible(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("The Conqueror");
    getContentPane().add(playerPanel, BorderLayout.PAGE_START);
    getContentPane().add(battleLog, BorderLayout.PAGE_END);
    getContentPane().add(centre, BorderLayout.CENTER);
    handleCentre(centre);
    handleBattlelog(battleLog);
    handleAttackerPanel(attackerPanel);
    handleDefenderPanel(defenderPanel);
	}
	
	public void handleCentre(JPanel centre) {
		centre.setLayout(new GridLayout(1,2));
		centre.add(attackerPanel);
		centre.add(defenderPanel);
	}
	
	public void handleBattlelog(JPanel battleLog) {
		
	}
	
	public void handleAttackerPanel(ArmyPanel attackerPanel){
		for(Unit unit : attackerArmy.getUnits()) {
			JPanel info = unitInformation(unit);
			attackerPanel.addCard(info);
		}
	}
	
	public void handleDefenderPanel(ArmyPanel defenderPanel) {
		for(Unit unit : attackerArmy.getUnits()) {
			JPanel info = unitInformation(unit);
			defenderPanel.addCard(info);
		}
	}
	
	public JPanel unitInformation(Unit unit) {
		JPanel info = new JPanel();
		String unitType;
		if(unit instanceof Archer)
			unitType = "Archer";
		else {
			if(unit instanceof Cavalry)
				unitType = "Cavalry";
			else
				unitType = "infantry";
		}
		JLabel text = new JLabel(unitType + "/n" + "Current Solider" + unit.getCurrentSoldierCount());
		info.add(text);
		return info;
	}
	
	public static void main(String[] args)throws IOException, FontFormatException, InvalidUnitException {
		Game test = new Game("ahmed", "cairo");
		Army test1 = test.getAvailableCities().get(0).getDefendingArmy();
		Army test2 = test.getAvailableCities().get(0).getDefendingArmy();
		new BattleView(null, new PlayerPanel(null), test1, test1).setVisible(true);
		
	}

}
