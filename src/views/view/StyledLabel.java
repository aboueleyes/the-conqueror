package views.view;

import java.awt.Font;

import javax.swing.JLabel;

public class StyledLabel extends JLabel {
  public StyledLabel(String s, int fontsize) {
    super(s);
    setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontsize));
  }
}
