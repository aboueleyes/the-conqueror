package views.panel;

import java.awt.BorderLayout;

//source ---> https://harmash.com/swing/swing-cardlayout/example-3.php

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import units.Unit;

public class CardsPanel extends JPanel implements ActionListener {

	JPanel panel_L;
	JPanel panel_R;
	private JButton next;

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public JButton getPrevious() {
		return previous;
	}

	public void setPrevious(JButton previous) {
		this.previous = previous;
	}

	private JButton previous;
	JButton first;
	JButton last;
	CardLayout card;
	DefendingUnitPanel defendingUnitPanel;

	public DefendingUnitPanel getDefendingUnitPanel() {
		return defendingUnitPanel;
	}

	public void setDefendingUnitPanel(DefendingUnitPanel defendingUnitPanel) {
		this.defendingUnitPanel = defendingUnitPanel;
	}

	public void addDefendingPanel(Unit unit, ActionListener a) {
		defendingUnitPanel = new DefendingUnitPanel(a, unit);
		unit.setUnitPanel(defendingUnitPanel);
		addCard(defendingUnitPanel);
	}

	public CardsPanel() {
		super();
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		panel_L = new JPanel();
		panel_R = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 1;

		add(panel_L, BorderLayout.PAGE_END);
		gbc.weightx = 10;
		add(panel_R, BorderLayout.CENTER);
		panel_L.setLayout(new GridLayout(1, 2));

		next = new JButton("Next");
		previous = new JButton("Previous");
		first = new JButton("First");
		last = new JButton("Last");

		panel_L.add(next);
		panel_L.add(previous);

		card = new CardLayout();
		panel_R.setLayout(card);

		next.addActionListener(this);
		previous.addActionListener(this);
		first.addActionListener(this);
		last.addActionListener(this);
		panel_R.setOpaque(false);

	}

	public void addCard(JPanel unit) {
		panel_R.add(unit);
		card.last(panel_R);
	}

	public void removeCard(JPanel unit) {
		panel_R.remove(unit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(next))
			card.next(panel_R);
		else {
			if (e.getSource().equals(previous))
				card.previous(panel_R);
			else {
				if (e.getSource().equals(first))
					card.first(panel_R);
				else
					card.last(panel_R);
			}
		}

	}

	public void clear() {
		panel_R.removeAll();
	}

	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setVisible(true);
		CardsPanel test2 = new CardsPanel();
		JPanel p = new JPanel();
		p.add(new JLabel("test"));
		JPanel p2 = new JPanel();
		p2.add(new JLabel("test2"));
		test2.addCard(p);
		test2.addCard(p2);
		test.add(test2);

	}

}
