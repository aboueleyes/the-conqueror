package views.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.*;
import views.panel.ImagePanel;

public class errorMessage extends JFrame{
    public errorMessage(){
        setVisible(true);
        setLayout(new BorderLayout());
        setSize(398, 350);
       // ImagePanel background = new ImagePanel(new ImageIcon("./assets/img/windows/errorMessage.png").getImage());
        //setContentPane(background);
      // setOpacity(0.5f);
      // pack();

       // setBackground(new Color(0, 0, 0, 0));
       
    JProgressBar  a= new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
    a.setValue(50);
    add(a,BorderLayout.NORTH);
   // a.setBackground(Color.green);
   a.setForeground(Color.green);

    }
    public static void main(String[] args) {
        new errorMessage();
    }
}
