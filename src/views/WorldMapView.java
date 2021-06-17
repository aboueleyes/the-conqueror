package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

public class WorldMapView  extends JFrame{
  private static final int PLAYER_PANEL_HEIGHT = 55;
  private static final int PLAYER_LABEL_SIZE = 20;
  private static final String COLOR_BEIGE = "#C8AE81";
  private JPanel armyPanel = new JPanel();
  private JPanel citiesPanel = new JPanel();
  private JPanel playerPanel = new JPanel();
  private StyledLabel playerName;
  private StyledLabel playerGold;
  private StyledLabel playerFood;
  private StyledLabel numOfTurns;
  private StyledButton endTurn;
  private JPanel armies = new JPanel();
  public StyledButton getEndTurn() {
    return endTurn;
  }
  public JPanel getArmies() {
    return armies;
  }
  public void setArmies(JPanel armies) {
    this.armies = armies;
  }
  public StyledLabel getNumOfTurns() {
    return numOfTurns;
  }
  public void setNumOfTurns(StyledLabel numOfTurns) {
    this.numOfTurns = numOfTurns;
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
    setTitle("The Conqueror"); pack();
    getContentPane().add(playerPanel,BorderLayout.PAGE_START);
    addPlayerPane();
    getContentPane().add(armyPanel,BorderLayout.PAGE_END);
    addArmyPane();
    getContentPane().add(citiesPanel,BorderLayout.CENTER);
    addCitiesPane();
   
    
   
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
  public void setPlayerPanel(JPanel playerPanel) {
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
    playerPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, PLAYER_PANEL_HEIGHT));
    // playerPanel.setBackground(Color.decode(COLOR_BEIGE));
    StyledLabel name = new StyledLabel("Player Name :",PLAYER_LABEL_SIZE,true);
    playerName = new StyledLabel("",PLAYER_LABEL_SIZE,false);
    playerPanel.add(name);
    playerPanel.add(playerName);
    name.setBorder(new EmptyBorder(10,10,10,10));
    playerName.setBorder(new EmptyBorder(10,10,10,50));
    StyledLabel gold = new StyledLabel("Gold :",PLAYER_LABEL_SIZE,true);
    playerGold = new StyledLabel("",PLAYER_LABEL_SIZE,true);
    StyledLabel food = new StyledLabel("Food :" ,PLAYER_LABEL_SIZE,true);
    StyledLabel turns = new StyledLabel("Current Turn :" ,PLAYER_LABEL_SIZE,true);
    playerFood = new StyledLabel("",PLAYER_LABEL_SIZE,true);
    gold.setBorder(new EmptyBorder(10,10,10,10));
    playerGold.setBorder(new EmptyBorder(10,10,10,50));
    food.setBorder(new EmptyBorder(10,10,10,10));
    playerFood.setBorder(new EmptyBorder(10,10,10,50));
    turns.setBorder(new EmptyBorder(10,10,10,10));
    numOfTurns = new StyledLabel("",PLAYER_LABEL_SIZE,true);
    numOfTurns.setBorder(new EmptyBorder(10,10,10,800));
    endTurn = new StyledButton("End Turn",PLAYER_LABEL_SIZE);
  

    playerPanel.add(gold);
    playerPanel.add(playerGold);
    playerPanel.add(food);
    playerPanel.add(playerFood);
    playerPanel.add(turns);
    playerPanel.add(numOfTurns);
    playerPanel.add(endTurn);

  } 

  public void addArmyPane() throws FontFormatException, IOException{
    armyPanel.setLayout(new BoxLayout(armyPanel,BoxLayout.Y_AXIS));
    armyPanel.setPreferredSize(new DimensionUIResource(JFrame.WIDTH, 400));
    armyPanel.setBackground(Color.decode(COLOR_BEIGE));
    armyPanel.add(new StyledLabel("Controled Armies",25,true));
    armies.setLayout(new BoxLayout(armies,BoxLayout.X_AXIS));
    JScrollPane scroller = new JScrollPane(armies,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    armies.setBackground(Color.decode(COLOR_BEIGE));
    armyPanel.add(scroller);

    
  }

  public void addCitiesPane(){
    citiesPanel.setLayout(new BorderLayout());
    ImagePanel cairoPanel =new ImagePanel(new ImageIcon("src/images/maxresdefault.jpg").getImage());
    ImagePanel romePanel =new ImagePanel(new ImageIcon("src/images/maxresdefault-1.jpg").getImage());
    ImagePanel spartaPanel =new ImagePanel(new ImageIcon("src/images/gdfg.jpg").getImage());
    cairoPanel.add(new JLabel("bfksdbfksa"));
    cairoPanel.setBounds(0,0,500,500);
  
    romePanel.setPreferredSize(new DimensionUIResource(600,JFrame.HEIGHT));
    spartaPanel.setPreferredSize(new DimensionUIResource(600,JFrame.HEIGHT));
    citiesPanel.add(romePanel,BorderLayout.WEST);
    citiesPanel.add(cairoPanel,BorderLayout.CENTER);
    citiesPanel.add(spartaPanel,BorderLayout.EAST);
  }
  public static void main(String[] args) throws FontFormatException, IOException {
   WorldMapView w = new WorldMapView();
   w.setVisible(true);
   w.playerName.setText("text");
   System.out.println(w.getWidth());
    
    
  }
}
