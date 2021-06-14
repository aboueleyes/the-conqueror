package views;

import javax.swing.JButton;
import java.awt.*;

public class StyledButton  extends JButton{

  public StyledButton(String s){
    super(s);
    Font font2 = new Font(Font.SERIF, Font.ITALIC, 14);
    Color color = new Color(153, 102, 0);
    setFont(font2);
    setForeground(Color.WHITE);
    setBackground(color);
  }
    
}
