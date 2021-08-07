package views.panel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import views.button.StyledButton;
import views.view.StyledLabel;

public class PlayerPanel extends JPanel {
    private StyledLabel playerName;
    private StyledLabel playerGold;
    private StyledLabel playerFood;
    private StyledLabel numOfTurns;
    private StyledButton back;
    private StyledButton endTurn;
    private static final int PLAYER_PANEL_HEIGHT = 45;
    private static final int PLAYER_LABEL_SIZE = 16;

    public PlayerPanel(ActionListener a) {

        setLayout(new BorderLayout());
        setPreferredSize(new DimensionUIResource(7, PLAYER_PANEL_HEIGHT));
        setBackground(Color.decode("#C8AE81"));
        var name = new StyledLabel("Player Name :", PLAYER_LABEL_SIZE);
        playerName = new StyledLabel("", PLAYER_LABEL_SIZE);

        name.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerName.setBorder(new EmptyBorder(10, 10, 10, 50));
        var gold = new StyledLabel("Gold :", PLAYER_LABEL_SIZE);
        playerGold = new StyledLabel("", PLAYER_LABEL_SIZE);
        var food = new StyledLabel("Food :", PLAYER_LABEL_SIZE);
        var turns = new StyledLabel("Current Turn :", PLAYER_LABEL_SIZE);
        playerFood = new StyledLabel("", PLAYER_LABEL_SIZE);
        gold.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerGold.setBorder(new EmptyBorder(10, 10, 10, 50));
        food.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerFood.setBorder(new EmptyBorder(10, 10, 10, 50));
        turns.setBorder(new EmptyBorder(10, 10, 10, 10));
        numOfTurns = new StyledLabel("", PLAYER_LABEL_SIZE);
        numOfTurns.setBorder(new EmptyBorder(10, 10, 10, 10));
        endTurn = new StyledButton("End Turn", PLAYER_LABEL_SIZE);
        endTurn.addActionListener(a);
        back = new StyledButton("Back", PLAYER_LABEL_SIZE);
        back.addActionListener(a);
        var infoJPanel = new JPanel();
        infoJPanel.setLayout(new BoxLayout(infoJPanel, BoxLayout.X_AXIS));
        infoJPanel.add(name);
        infoJPanel.add(playerName);
        infoJPanel.add(gold);
        infoJPanel.add(playerGold);
        infoJPanel.add(food);
        infoJPanel.add(playerFood);
        infoJPanel.add(turns);
        infoJPanel.add(numOfTurns);
        var buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.X_AXIS));
        buttonJPanel.add(endTurn);
        buttonJPanel.add(back);
        add(infoJPanel, BorderLayout.WEST);
        add(buttonJPanel, BorderLayout.EAST);
        buttonJPanel.setOpaque(false);
        infoJPanel.setOpaque(false);
        buttonJPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

    }

    public StyledButton getBack() {
        return back;
    }

    public void setBack(StyledButton back) {
        this.back = back;
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
