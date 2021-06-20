package views.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class StyledButton extends JButton {

  public StyledButton(String string) {

  }

  public StyledButton(String s, int size) {
    super(s);
    Color color = new Color(153, 102, 0);
    setFont(new Font(Font.MONOSPACED, Font.PLAIN, size));
    setForeground(Color.WHITE);
    setBackground(color);
    setBorder(new EmptyBorder(10, 10, 10, 10));
    setBorderPainted(false);
    setFocusPainted(false);
  }

}
