package views.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.DimensionUIResource;

import engine.Game;
import exceptions.InvalidUnitException;
import units.Army;
import units.ArmyListener;
import units.Cavalry;
import units.Unit;
import units.UnitListener;
import views.panel.CardsPanel;
import views.panel.PlayerPanel;
import views.panel.UnitPanel;

public class BattleView extends JFrame {

	private PlayerPanel playerPanle;
	private JPanel battleLog;
	private CardsPanel attackerPanel;
	private CardsPanel defenderPanel;
	private JPanel centre;
	private Army attackerArmy;
	private Army defenderArmy;
	private JButton attack;
	private JButton autoResolve;
    private  Unit attackingUnit;
	private  Unit defendingUnit;
	JTextArea log = new JTextArea();
	public PlayerPanel getPlayerPanle() {
		return playerPanle;
	}

	public void setPlayerPanle(PlayerPanel playerPanle) {
		this.playerPanle = playerPanle;
	}

	public JPanel getBattleLog() {
		return battleLog;
	}

	public void setBattleLog(JPanel battleLog) {
		this.battleLog = battleLog;
	}

	public CardsPanel getAttackerPanel() {
		return attackerPanel;
	}

	public void setAttackerPanel(CardsPanel attackerPanel) {
		this.attackerPanel = attackerPanel;
	}

	public CardsPanel getDefenderPanel() {
		return defenderPanel;
	}

	public void setDefenderPanel(CardsPanel defenderPanel) {
		this.defenderPanel = defenderPanel;
	}

	public JPanel getCentre() {
		return centre;
	}

	public void setCentre(JPanel centre) {
		this.centre = centre;
	}

	public Army getAttackerArmy() {
		return attackerArmy;
	}

	public void setAttackerArmy(Army attackerArmy) {
		this.attackerArmy = attackerArmy;
	}

	public Army getDefenderArmy() {
		return defenderArmy;
	}

	public void setDefenderArmy(Army defenderArmy) {
		this.defenderArmy = defenderArmy;
	}

	public JButton getAttack() {
		return attack;
	}

	public void setAttack(JButton attack) {
		this.attack = attack;
	}

	public JButton getAutoResolve() {
		return autoResolve;
	}

	public void setAutoResolve(JButton autoResolve) {
		this.autoResolve = autoResolve;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public BattleView(ActionListener a, PlayerPanel playerPanel, Army attackerArmy, Army defenderArmy) {
		super();
		this.playerPanle = playerPanel;
		this.attackerPanel = new CardsPanel();
		this.defenderPanel = new CardsPanel();
		this.centre = new JPanel();
		this.attackerArmy = attackerArmy;
		this.defenderArmy = defenderArmy;
		this.battleLog = new JPanel();
		this.attack = new JButton("Attack");
		attack.addActionListener(a);
		this.attack.setFont(new Font("Dialog", Font.PLAIN, 20));
		// this.attack.setPreferredSize(new Dimension(1600,40));
		this.autoResolve = new JButton("Auto Resolve");
		this.autoResolve.setFont(new Font("Dialog", Font.PLAIN, 20));
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
		handleAttackerPanel(attackerPanel, a);
		handleDefenderPanel(defenderPanel, a);
		defenderArmy.setArmyListener((ArmyListener) a);
		attackerArmy.setArmyListener((ArmyListener) a);
	}

	public Unit getDefendingUnit() {
		return defendingUnit;
	}

	public void setDefendingUnit(Unit defendingUnit) {
		this.defendingUnit = defendingUnit;
	}

	public Unit getAttackingUnit() {
		return attackingUnit;
	}

	public void setAttackingUnit(Unit attackingUnit) {
		this.attackingUnit = attackingUnit;
	}

	public void handleCentre(JPanel centre) {
		centre.setLayout(new BorderLayout());
		JPanel south = new JPanel();
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1, 2));
		north.add(attack);
		north.add(autoResolve);
		north.setPreferredSize(new Dimension(100, 100));
		centre.add(north, BorderLayout.PAGE_START);
		centre.add(south, BorderLayout.CENTER);
		south.setLayout(new GridLayout(1, 2));
		south.add(attackerPanel);
		south.add(defenderPanel);
	}

	public void handleBattlelog(JPanel battleLog) {
		battleLog.setLayout(new BorderLayout());
		JLabel head = new JLabel("Battle Log");
		battleLog.add(head, BorderLayout.PAGE_START);
		battleLog.add(log, BorderLayout.CENTER);
		battleLog.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
	}

	public void handleAttackerPanel(CardsPanel attackerPanel, ActionListener a) {
		for (Unit unit : attackerArmy.getUnits()) {
			UnitPanel info = new UnitPanel(a, unit);
			unit.setBattleUnitPanel(info);
			info.getAction1().setActionCommand("selectAttacker");
			attackerPanel.addCard(info);
			unit.setUnitListener((UnitListener) a);
		}
	}

	public void handleDefenderPanel(CardsPanel defenderPanel, ActionListener a) {
		for (Unit unit : defenderArmy.getUnits()) {
			UnitPanel info = new UnitPanel(a, unit);
			unit.setBattleUnitPanel(info);
			info.getAction1().setActionCommand("selectDefender");
			defenderPanel.addCard(info);
			unit.setUnitListener((UnitListener) a);

		}
	}

	// public JPanel unitInformation(Unit unit) {
	// ImagePanel info = setUnitPanelTypeImage(unit);
	// String[] unitInfo = unit.toString().split("\n");
	// String line1 = unitInfo[0];
	// String line2 = unitInfo[1];
	// String line3 = unitInfo[2];
	// JLabel text = new JLabel();
	// text.setText("<html>" + line1 + "<br>" + line2 + "<br>" + line3);
	// text.setFont(new Font("Dialog", Font.BOLD, 20));
	// info.add(text);
	// return info;
	// }

	// private ImagePanel setUnitPanelTypeImage(Unit unit) {
	// ImagePanel info;
	// if (unit instanceof Archer) {
	// info = new ImagePanel(new ImageIcon("src/images/archer.jpg").getImage());
	// } else if (unit instanceof Cavalry) {
	// info = new ImagePanel(new ImageIcon("src/images/cavalry.jpg").getImage());
	// } else {
	// info = new ImagePanel(new ImageIcon("src/images/infantry.jpg").getImage());
	// }
	// return info;
	// }

	public static void main(String[] args) throws IOException, InvalidUnitException {
		Game test = new Game("ahmed", "cairo");
		Army test1 = test.getAvailableCities().get(0).getDefendingArmy();
		new BattleView(null, new PlayerPanel(null), test1, test1).setVisible(true);

	}

}
