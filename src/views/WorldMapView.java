package views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WorldMapView  extends JFrame{
  JPanel playerPanel= new JPanel();
  JPanel armyPanel;
  JPanel citiesPanel;

  public WorldMapView(){
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(false);
    setTitle("The Conqueror");
    playerPanel.setBounds(0, 0, getWidth()/4, getHeight());
    add(playerPanel);
    playerPanel.setBackground(Color.BLUE);
  }
}
