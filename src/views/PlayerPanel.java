package views;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import java.awt.event.*;
import java.io.IOException;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Color.*;
public class PlayerPanel extends JPanel{
    private StyledLabel playerName;
    private StyledLabel playerGold;
    private StyledLabel playerFood;
    private StyledLabel numOfTurns;
    private StyledButton endTurn;

    public PlayerPanel(ActionListener a) throws FontFormatException, IOException{
    
            setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
            setPreferredSize(new DimensionUIResource(7, 100));
            setBackground(Color.decode("#C8AE81"));
            StyledLabel name = new StyledLabel("Player Name :",25,true);
            playerName = new StyledLabel("",25,false);
            add(name);
            add(playerName);
            name.setBorder(new EmptyBorder(10,10,10,10));
            playerName.setBorder(new EmptyBorder(10,10,10,50));
            StyledLabel gold = new StyledLabel("Gold :",25,true);
            playerGold = new StyledLabel("",25,true);
            StyledLabel food = new StyledLabel("Food :" ,25,true);
            StyledLabel turns = new StyledLabel("Current Turn :" ,25,true);
            playerFood = new StyledLabel("",25,true);
            gold.setBorder(new EmptyBorder(10,10,10,10));
            playerGold.setBorder(new EmptyBorder(10,10,10,50));
            food.setBorder(new EmptyBorder(10,10,10,10));
            playerFood.setBorder(new EmptyBorder(10,10,10,50));
            turns.setBorder(new EmptyBorder(10,10,10,10));
            numOfTurns = new StyledLabel("",25,true);
            numOfTurns.setBorder(new EmptyBorder(10,10,10,800));
            endTurn = new StyledButton("End Turn",25);
            endTurn.addActionListener(a);
        
            add(gold);
            add(playerGold);
            add(food);
            add(playerFood);
            add(turns);
            add(numOfTurns);
            add(endTurn);
        
          
    }    
    public StyledLabel getPlayerName() {
        return playerName;
    }
    public StyledButton getEndTurn() {
        return endTurn;
    }
    public void setEndTurn(StyledButton endTurn) {
        this.endTurn = endTurn;
    }
    public StyledLabel getNumOfTurns() {
        return numOfTurns;
    }
    public void setNumOfTurns(StyledLabel numOfTurns) {
        this.numOfTurns = numOfTurns;
    }
    public StyledLabel getPlayerFood() {
        return playerFood;
    }
    public void setPlayerFood(StyledLabel playerFood) {
        this.playerFood = playerFood;
    }
    public StyledLabel getPlayerGold() {
        return playerGold;
    }
    public void setPlayerGold(StyledLabel playerGold) {
        this.playerGold = playerGold;
    }
    public void setPlayerName(StyledLabel playerName) {
        this.playerName = playerName;
    }

}