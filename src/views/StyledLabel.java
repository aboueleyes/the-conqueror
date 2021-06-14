package views;

import javax.swing.JLabel;
import java.awt.*;
public class StyledLabel extends JLabel{
  public StyledLabel (String s,int fontsize, boolean bold){
    super(s);
    Font font;
    if(bold){
     font = new Font(Font.SERIF, Font.ITALIC|Font.BOLD, fontsize);
    }
    else{
      font = new Font(Font.SERIF, Font.ITALIC, fontsize);
    }
    setFont(font);
    }
}
