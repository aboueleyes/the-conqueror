package views.panel;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import views.button.StyledButton;
import views.view.StyledLabel;

import java.awt.event.*;
import java.io.IOException;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Color.*;

public class PlayerPanel extends JPanel {
    private StyledLabel playerName;
    private StyledLabel playerGold;
    private StyledLabel playerFood;
    private StyledLabel numOfTurns;
    private StyledButton endTurn;
    private static final int PLAYER_PANEL_HEIGHT = 55;
    private static final int PLAYER_LABEL_SIZE = 20;

    public PlayerPanel(ActionListener a) throws FontFormatException, IOException {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new DimensionUIResource(7, PLAYER_PANEL_HEIGHT));
        setBackground(Color.decode("#C8AE81"));
        StyledLabel name = new StyledLabel("Player Name :", PLAYER_LABEL_SIZE, true);
        playerName = new StyledLabel("", PLAYER_LABEL_SIZE, false);
        add(name);
        add(playerName);
        name.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerName.setBorder(new EmptyBorder(10, 10, 10, 50));
        StyledLabel gold = new StyledLabel("Gold :", PLAYER_LABEL_SIZE, true);
        playerGold = new StyledLabel("", PLAYER_LABEL_SIZE, true);
        StyledLabel food = new StyledLabel("Food :", PLAYER_LABEL_SIZE, true);
        StyledLabel turns = new StyledLabel("Current Turn :", PLAYER_LABEL_SIZE, true);
        playerFood = new StyledLabel("", PLAYER_LABEL_SIZE, true);
        gold.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerGold.setBorder(new EmptyBorder(10, 10, 10, 50));
        food.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerFood.setBorder(new EmptyBorder(10, 10, 10, 50));
        turns.setBorder(new EmptyBorder(10, 10, 10, 10));
        numOfTurns = new StyledLabel("", PLAYER_LABEL_SIZE, true);
        numOfTurns.setBorder(new EmptyBorder(10, 10, 10, 800));
        endTurn = new StyledButton("End Turn", PLAYER_LABEL_SIZE);
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