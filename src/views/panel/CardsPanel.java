package views.panel;

//source ---> https://harmash.com/swing/swing-cardlayout/example-3.php

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardsPanel extends JPanel implements ActionListener{
	
	JPanel panel_L;
	JPanel panel_R;
	JButton next;
	JButton previous;
	JButton first;
	JButton last;
	CardLayout card;
	
	public CardsPanel() {
		super();
		this.setLayout(new GridLayout());
		this.setVisible(true);
		panel_L = new JPanel();
	  panel_R = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
    gbc.weighty = 1;
    
    add(panel_L, gbc);
    gbc.weightx = 10;
    add(panel_R, gbc);
    panel_L.setLayout(new GridLayout(5, 1));
    
    
    next = new JButton("Next");
    previous = new JButton("Previous");
    first = new JButton("First");
    last = new JButton("Last");
 
    
    panel_L.add(next);
    panel_L.add(previous);
    panel_L.add(first);
    panel_L.add(last);
    
    card = new CardLayout();
    panel_R.setLayout(card);
    
    next.addActionListener(this);
    previous.addActionListener(this);
    first.addActionListener(this);
    last.addActionListener(this);

    
	}
	
	 public void addCard(JPanel unit) {
   	panel_R.add(unit);
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(next))
			card.next(panel_R);
		else {
			if(e.getSource().equals(previous))
				card.previous(panel_R);
			else {
				if(e.getActionCommand().equals("first"))
					card.first(panel_R);
				else
					card.last(panel_R);
			}
		}
		
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
