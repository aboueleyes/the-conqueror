package views;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StyledButton  extends JButton{
  public StyledButton(){
    
  }
  public StyledButton(String s, int size) throws FontFormatException, IOException{
    super(s);
    File fontFile = new File("src/fonts/DeanMartinRegular-4Llx.ttf");
    Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    font1 = font1 .deriveFont(Font.ITALIC| Font.BOLD,size);
    Color color = new Color(153, 102, 0);
    setFont(font1);
    setForeground(Color.WHITE);
    setBackground(color);
    setBorder(new EmptyBorder(10,10,10,10));
    setBorderPainted(false);
    setFocusPainted(false);
    //setContentAreaFilled(false);
  }
    
}
