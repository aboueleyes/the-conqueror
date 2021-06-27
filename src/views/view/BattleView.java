package views.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.DimensionUIResource;

import engine.Game;
import exceptions.InvalidUnitException;
import units.Army;
import units.ArmyListener;
import units.Unit;
import units.UnitListener;
import views.button.StyledButton;
import views.panel.CardsPanel;
import views.panel.DefendingUnitPanel;
import views.panel.ImagePanel;
import views.panel.PlayerPanel;
import views.panel.UnitPanel;

public class BattleView extends JFrame {

	private PlayerPanel playerPanel;
	private JPanel battleLog;
	private CardsPanel attackerPanel;
	private CardsPanel defenderPanel;
	private JPanel centre;
	private Army attackerArmy;
	private Army defenderArmy;
	private JButton attack;
	private JButton autoResolve;
	private Unit attackingUnit;
	private Unit defendingUnit;
	JTextArea log = new JTextArea();

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
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
		this.playerPanel = playerPanel;
		this.attackerPanel = new CardsPanel();
		this.defenderPanel = new CardsPanel();
		this.centre = new JPanel();
		this.attackerArmy = attackerArmy;
		this.defenderArmy = defenderArmy;
		this.battleLog = new JPanel();
		this.attack = new StyledButton("Attack",20);
		attack.addActionListener(a);
		
		this.autoResolve = new StyledButton("Auto Resolve",20);
		
		this.setLayout(new BorderLayout());
		autoResolve.addActionListener(a);
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
		attack.setEnabled(false);
		
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
        JLabel label1 = new JLabel("<html><h1><strong><i>" + "Your Army" + "</i></strong></h1><hr></html>");
		JLabel label2 = new JLabel("<html><h1><strong><i>" + "Defending Army" + "</i></strong></h1><hr></html>");
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		right.add(label1,BorderLayout.PAGE_START);
		right.add(attackerPanel);
		left.add(label2,BorderLayout.PAGE_START);
		left.add(defenderPanel);

		JPanel south = new JPanel();
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1, 2));
		north.add(right);
		north.add(left);
		//north.setPreferredSize(new Dimension(100, 100));
		centre.add(north);
		centre.add(south, BorderLayout.SOUTH);
		south.setLayout(new GridLayout(1, 6));
		south.add(new JLabel());
		south.add(new JLabel());
		south.add(attack);
		south.add(autoResolve);
		south.add(new JLabel());
		south.add(new JLabel());
	}

	public void handleBattlelog(JPanel battleLog) {
		battleLog.setLayout(new BorderLayout());
		JLabel head = new JLabel("<html><h1><strong><i>" + "Battle Log" + "</i></strong></h1><hr></html>");
		ImagePanel background = new ImagePanel(new ImageIcon("./assets/img/windows/battlelog.png").getImage());
		
		//JPanel background = new JPanel();
		background.setLayout(new BorderLayout());
		//background.setBackground(Color.black);
		battleLog.add(head, BorderLayout.NORTH);
		//battleLog.add(background,BorderLayout.CENTER);
		battleLog.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
		log.setEditable(false);
		log.setOpaque(false);
		JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scroll.setOpaque(false);	
		        scroll.getViewport().setOpaque(false);
		background.add(scroll,BorderLayout.CENTER);
		battleLog.add(background,BorderLayout.CENTER);
		log.setFont(new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, 13));
		log.setBorder(new EmptyBorder(50,50,30,30));
		scroll.setBorder(new EmptyBorder(50,50,30,30));
		//battleLog.setOpaque(false);
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

	public static void main(String[] args) throws IOException, InvalidUnitException {
		Game test = new Game("ahmed", "cairo","easy");
		Army test1 = test.getAvailableCities().get(0).getDefendingArmy();
		new BattleView(null, new PlayerPanel(null), test1, test1).setVisible(true);

	}

}
