package views;

import java.awt.event.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.MyInputVerifier;
import engine.Game;
import utlis.ReadingCSVFile;

public class StartView extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JTextField nameOfPlayer;
    private JComboBox<String> cityOfPlayer;
    private JButton start;
    private ImagePanel panel;
    private Game game;
    private static ArrayList<String> citiesName = new ArrayList<>();

    public static String[] getStringArray(ArrayList<String> arr) {

        String str[] = new String[arr.size()];
        arr.forEach(n -> str[arr.indexOf(n)] = n);
        return str;
    }

    public void loadCitiesAndDistances() throws IOException {
        List<List<String>> data = ReadingCSVFile.readFile("distances.csv");

        for (List<String> line : data) {
            String from = line.get(0);
            String to = line.get(1);
            addToSet(to);
            addToSet(from);
        }
    }

    private void addToSet(String name) {
        if (!citiesName.contains(name)) {
            citiesName.add(name);
        }
    }

    public StartView(ActionListener a) throws FontFormatException, IOException {
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

    private void setComponent() throws FontFormatException, IOException {
        loadCitiesAndDistances();
        setLabel1(new StyledLabel("Enter your name", 20, true));
        setNameOfPlayer(new JTextField());
        setLabel2(new StyledLabel("Choose yourCity", 20, true));
        nameOfPlayer.setInputVerifier(new MyInputVerifier());
        setCityOfPlayer(new JComboBox<>(getStringArray(citiesName)));
        setStart(new StyledButton("Start",16));
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