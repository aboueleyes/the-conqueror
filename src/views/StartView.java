package views;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Controller;
import controllers.MyInputVerifier;
import engine.Game;
import exceptions.InvalidUnitException;

public class StartView extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JTextField nameOfPlayer;
    private JComboBox<String> cityOfPlayer;
    private JButton start;
    private ImagePanel panel;
    private Game game;
    private static final String[] citiesName = { "Cairo", "Rome", "Sparta" };

    public StartView(ActionListener a) {
        setSize(500, 500);
        setComponent();
        addComponents();
        sizeComponents();

        panel.setSize(new Dimension(25, 50));
        panel.setLayout(null);

        setTitle("TheConqueror");
        start.addActionListener(a);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void setComponent() {
        setLabel1(new StyledLabel("Enter your name",16,true));
        setNameOfPlayer(new JTextField());
        setLabel2(new StyledLabel("Choose yourCity",16,true));
        nameOfPlayer.setInputVerifier(new MyInputVerifier());
        setCityOfPlayer(new JComboBox<>(citiesName));
        setStart(new StyledButton("Start"));
        panel = new ImagePanel(new ImageIcon("src/images/1110988.jpg").getImage());
    }

    private void addComponents() {
        panel.add(label1);
        panel.add(nameOfPlayer);
        panel.add(label2);
        panel.add(cityOfPlayer);
        panel.add(start);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void sizeComponents() {
        Font font1 = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 16);
        Font font2 = new Font(Font.SERIF, Font.ITALIC, 14);
        label1.setBounds(50, 50, 400, 20);
        nameOfPlayer.setBounds(50, 75, 400, 20);
        nameOfPlayer.setFont(font2);
        label2.setBounds(50, 200, 400, 20);
        cityOfPlayer.setBounds(50, 250, 400, 20);
        cityOfPlayer.setFont(font2);
        cityOfPlayer.setSelectedIndex(-1);
        start.setBounds(200, 350, 100, 20);
        
    }

    public JButton getStart() {
        return start;
    }

    public void setStart(StyledButton start) {
        this.start = start;
    }

    public JComboBox<String> getCityOfPlayer() {
        return cityOfPlayer;
    }

    public void setCityOfPlayer(JComboBox<String> cityOfPlayer) {
        this.cityOfPlayer = cityOfPlayer;
    }

    public JTextField getNameOfPlayer() {
        return nameOfPlayer;
    }

    public void setNameOfPlayer(JTextField nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public void setLabel2(JLabel label2) {
        this.label2 = label2;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public void setLabel1(JLabel label1) {
        this.label1 = label1;
    }


}