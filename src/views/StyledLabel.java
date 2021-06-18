package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class StyledLabel extends JLabel{
  public StyledLabel (String s,int fontsize, boolean bold) throws FontFormatException, IOException{
    super(s);
    Font font;
    File fontFile = new File("src/fonts/DeanMartinRegular-4Llx.ttf");
    Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    
    if(bold){
      font1 = font1.deriveFont(Font.ITALIC|Font.BOLD,fontsize);

    }
    else{
      font1 = font1.deriveFont(Font.ITALIC,fontsize);
    }
    setFont(font1);
    }
    public static void main(String[] args) throws FontFormatException, IOException {
      JFrame test = new JFrame();
      test.setVisible(true);
    
        test.getContentPane().add(new StyledLabel("hello",16,true));
     
    }
}
