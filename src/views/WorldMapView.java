package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WorldMapView  extends JFrame{
  private JPanel armyPanel;
  private JPanel citiesPanel;
  private JPanel playerPanel = new JPanel();
  private StyledLabel playerName;

  public StyledLabel getPlayerName() {
    return playerName;
  }
  public void setPlayerName(StyledLabel playerName) {
    this.playerName = playerName;
  }
  public WorldMapView() throws FontFormatException, IOException{
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("The Conqueror");
    getContentPane().add(playerPanel,BorderLayout.PAGE_START);
    playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.X_AXIS));
   // Color playerPnlBg = new Color (#BE6D6A);
    playerPanel.setBackground(Color.decode("#C8AE81"));
    StyledLabel name = new StyledLabel("Player Name :",25,true);
    playerName = new StyledLabel("",25,false);
    playerPanel.add(name);
    playerPanel.add(playerName);
    name.setBorder(new EmptyBorder(10,10,10,10));
    playerName.setBorder(new EmptyBorder(10,10,10,10));
  }
  public JPanel getCitiesPanel() {
    return citiesPanel;
  }
  public void setCitiesPanel(JPanel citiesPanel) {
    this.citiesPanel = citiesPanel;
  }
  public JPanel getPlayerPanel() {
    return playerPanel;
  }
  public void setPlayerPanel(JPanel palyerPanel) {
    this.playerPanel = playerPanel;
  }
  public JPanel getArmyPanel() {
    return armyPanel;
  }
  public void setArmyPanel(JPanel armyPanel) {
    this.armyPanel = armyPanel;
  }
  public static void main(String[] args) throws FontFormatException, IOException {
   WorldMapView w = new WorldMapView();
   w.setVisible(true);
   w.playerName.setText("text");
    
    
  }
}
