package views.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import views.button.StyledButton;
import views.panel.ImagePanel;

public class EndGameView extends JFrame {

	ImagePanel background;
	StyledButton endGame;
	StyledButton playAgain;

	public EndGameView(ActionListener a) {
		super();
		background = new ImagePanel("./assets/img/windows/endGame.jpg");
		this.setSize(500, 300);
		JPanel buttonPanel = new JPanel();
		this.add(background);
		buttonPanel.setLayout(new FlowLayout());
		endGame = new StyledButton("end game", 20);
		endGame.addActionListener(a);
		playAgain = new StyledButton("play again", 20);
		playAgain.addActionListener(a);
		buttonPanel.add(endGame);
		buttonPanel.add(playAgain);
		buttonPanel.setOpaque(false);
		background.setLayout(new BorderLayout());
		background.add(buttonPanel, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void addLabel(JLabel text) {
		this.background.add(text, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		EndGameView test = new EndGameView(null);
		JLabel text = new JLabel("text");
		text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		test.addLabel(text);
		test.setVisible(true);
	}

}
