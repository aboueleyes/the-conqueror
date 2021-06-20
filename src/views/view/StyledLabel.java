package views.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;

public class StyledLabel extends JLabel {
  public StyledLabel(String s, int fontsize, boolean bold) {
    super(s);
    setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontsize));
  }
}
