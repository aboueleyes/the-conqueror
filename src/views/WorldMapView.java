package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

public class WorldMapView  extends JFrame{
  private JPanel armyPanel;
  private JPanel citiesPanel;
  private JPanel playerPanel = new JPanel();
  private StyledLabel playerName;
  private StyledLabel playerGold;
  private StyledLabel playerFood;
  private StyledButton endTurn;
  public StyledButton getEndTurn() {
    return endTurn;
  }
  public void setEndTurn(StyledButton endTurn) {
    this.endTurn = endTurn;
  }
  public StyledLabel getPlayerGold() {
    return playerGold;
  }
  public void setPlayerGold(StyledLabel playerGold) {
    this.playerGold = playerGold;
  }
  public StyledLabel getPlayerFood() {
    return playerFood;
  }
  public void setPlayerFood(StyledLabel playerFood) {
    this.playerFood = playerFood;
  }
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
    addPlayerPane();
    
   
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
  public void addPlayerPane() throws FontFormatException, IOException{
    playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.X_AXIS));
    playerPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 80));
    playerPanel.setBackground(Color.decode("#C8AE81"));
    StyledLabel name = new StyledLabel("Player Name :",25,true);
    playerName = new StyledLabel("",25,false);
    playerPanel.add(name);
    playerPanel.add(playerName);
    name.setBorder(new EmptyBorder(10,10,10,10));
    playerName.setBorder(new EmptyBorder(10,10,10,50));
    StyledLabel gold = new StyledLabel("Gold :",25,true);
    playerGold = new StyledLabel("",25,true);
    StyledLabel food = new StyledLabel("Food :" ,25,true);
    playerFood = new StyledLabel("",25,true);
    gold.setBorder(new EmptyBorder(10,10,10,10));
    playerGold.setBorder(new EmptyBorder(10,10,10,50));
    food.setBorder(new EmptyBorder(10,10,10,10));
    playerFood.setBorder(new EmptyBorder(10,10,10,1000));
    endTurn = new StyledButton("End Turn",25);
  

    playerPanel.add(gold);
    playerPanel.add(playerGold);
    playerPanel.add(food);
    playerPanel.add(playerFood);
    playerPanel.add(endTurn);

  }
  public static void main(String[] args) throws FontFormatException, IOException {
   WorldMapView w = new WorldMapView();
   w.setVisible(true);
   w.playerName.setText("text");
    
    
  }
}
