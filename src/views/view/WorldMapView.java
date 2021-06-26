package views.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import engine.City;
import views.button.CityButton;
import views.panel.CardsPanel;
import views.panel.ImagePanel;
import views.panel.PlayerPanel;

public class WorldMapView extends JFrame {
  private static final String COLOR_BEIGE = "#C8AE81";
  private JPanel armyPanel = new JPanel();
  private JPanel citiesPanel = new JPanel();
  private PlayerPanel playerPanel;
  private JPanel armies = new JPanel();
  private CityButton cairoButton;
  private CityButton romeButton;
  private CityButton spartaButton;
  private CardsPanel armyCards;
  private CardsPanel unitsCard;

  public CardsPanel getArmyCards() {
    return armyCards;
  }

  public CardsPanel getUnitsCard() {
    return unitsCard;
  }

  public void setUnitsCard(CardsPanel unitsCard) {
    this.unitsCard = unitsCard;
  }

  public void setArmyCards(CardsPanel armyCards) {
    this.armyCards = armyCards;
  }

  public JPanel getArmies() {
    return armies;
  }

  public CityButton getSpartaButton() {
    return spartaButton;
  }

  public void setSpartaButton(CityButton spartaButton) {
    this.spartaButton = spartaButton;
  }

  public CityButton getRomeButton() {
    return romeButton;
  }

  public void setRomeButton(CityButton romeButton) {
    this.romeButton = romeButton;
  }

  public CityButton getCairoButton() {
    return cairoButton;
  }

  public void setCairoButton(CityButton cairoButton) {
    this.cairoButton = cairoButton;
  }

  public void setArmies(JPanel armies) {
    this.armies = armies;
  }

  public WorldMapView(ActionListener a, PlayerPanel playerpanel) {
    this.playerPanel = playerpanel;
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("The Conqueror");
    pack();
    getContentPane().add(playerPanel, BorderLayout.PAGE_START);
    getContentPane().add(armyPanel, BorderLayout.PAGE_END);
    addArmyPane();
    getContentPane().add(citiesPanel, BorderLayout.CENTER);
    addCitiesPane(a);
    armyCards.getNext().addActionListener(a);
    armyCards.getPrevious().addActionListener(a);
    unitsCard.setOpaque(false);

  }

  public JPanel getCitiesPanel() {
    return citiesPanel;
  }

  public void setCitiesPanel(JPanel citiesPanel) {
    this.citiesPanel = citiesPanel;
  }

  public PlayerPanel getPlayerPanel() {
    return playerPanel;
  }

  public void setPlayerPanel(PlayerPanel playerPanel) {
    this.playerPanel = playerPanel;
  }

  public JPanel getArmyPanel() {
    return armyPanel;
  }

  public void setArmyPanel(JPanel armyPanel) {
    this.armyPanel = armyPanel;
  }

  public void addArmyPane() {
    armyPanel.setLayout(new BorderLayout());
    armyPanel.setPreferredSize(new DimensionUIResource(WIDTH, 400));
    //armyPanel.setBackground(Color.decode(COLOR_BEIGE));
    JLabel label = new JLabel("<html><h1><strong><i>" + "Controlled Armies" + "</i></strong></h1><hr></html>");
    label.setBorder(new EmptyBorder(10, 10, 10, 10));
    armyPanel.add(label, BorderLayout.PAGE_START);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 3));
    unitsCard = new CardsPanel();
    unitsCard.setVisible(false);
    armyCards = new CardsPanel();
    armyCards.setOpaque(false);
    panel.add(armyCards);
    panel.add(new JLabel());
    panel.add(unitsCard);
    panel.setOpaque(false);
    armyPanel.add(panel);

  }

  public void addCitiesPane(ActionListener a) {
    citiesPanel.setLayout(new BorderLayout());
    //citiesPanel.setBackground(Color.decode("#C8AE81"));
    ImagePanel cairoPanel = new ImagePanel(new ImageIcon("./assets/img/cities/cairo.png").getImage());
    ImagePanel romePanel = new ImagePanel(new ImageIcon("./assets/img/cities/rome.png").getImage());
    ImagePanel spartaPanel = new ImagePanel(new ImageIcon("./assets/img/cities/sparta.png").getImage());

    cairoPanel.setLayout(new BorderLayout());
    StyledLabel cairoName =  new StyledLabel("Cairo",70,true);
    cairoButton = new CityButton("view", 15);
    cairoButton.addActionListener(a);
    JPanel buttonArea = new JPanel();
    buttonArea.setLayout(new BorderLayout());
    buttonArea.add(cairoButton, BorderLayout.EAST);
    JPanel panel3 = new JPanel();
    panel3.setLayout(new GridLayout(3,1));
    panel3.add(cairoName) ;
    panel3.add(new JLabel());
    JPanel bottom = new JPanel();
    bottom.setLayout(new BorderLayout());
    bottom.add(buttonArea,BorderLayout.PAGE_END);
    panel3.add(bottom);
    cairoPanel.add(panel3, BorderLayout.SOUTH);

    spartaPanel.setLayout(new BorderLayout());
    StyledLabel spartaName =  new StyledLabel("Sparta",70,true);
    //spartaName.setForeground(Color.WHITE);
    spartaButton = new CityButton("view", 15);
    spartaButton.setActionCommand("sparta");
    spartaButton.addActionListener(a);
    JPanel buttonArea2 = new JPanel();
    buttonArea2.setLayout(new BorderLayout());
    spartaName.setOpaque(false);
    buttonArea2.add(spartaButton,BorderLayout.EAST);
    buttonArea2.setOpaque(false);
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(3,1));
    panel1.add(spartaName) ;
    panel1.add(new JLabel(""));
    JPanel bottom1 = new JPanel();
    bottom1.setLayout(new BorderLayout());
    bottom1.add(buttonArea2,BorderLayout.PAGE_END);
    panel1.add(bottom1);
    panel1.setOpaque(false);
    spartaPanel.add(panel1, BorderLayout.SOUTH);
   
  

    romePanel.setLayout(new BorderLayout());
    StyledLabel romeName =  new StyledLabel("Rome",70,true);
    //romeName.setForeground(Color.WHITE);
    romePanel.add(romeName, BorderLayout.LINE_START);
    romeButton = new CityButton("view", 15);
    JPanel buttonArea3 = new JPanel();
    buttonArea3.setLayout(new BorderLayout());
    buttonArea3.add(romeButton, BorderLayout.EAST);
   
    buttonArea3.setOpaque(false);
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayout(3,1));
    panel2.add(romeName) ;
    panel2.add(new JLabel(""));
    JPanel bottom3 = new JPanel();
    bottom3.setLayout(new BorderLayout());
    bottom3.add(buttonArea3,BorderLayout.PAGE_END);
    panel2.add(bottom3);
    romePanel.add(panel2, BorderLayout.SOUTH);
    romeButton.addActionListener(a);

    romePanel.setPreferredSize(new DimensionUIResource(600, JFrame.HEIGHT));
    spartaPanel.setPreferredSize(new DimensionUIResource(600, JFrame.HEIGHT));
    citiesPanel.add(romePanel, BorderLayout.WEST);
    citiesPanel.add(cairoPanel, BorderLayout.CENTER);
    citiesPanel.add(spartaPanel, BorderLayout.EAST);

    cairoButton.setActionCommand("Cairo");
    spartaButton.setActionCommand("Sparta");
    romeButton.setActionCommand("Rome");
  }

  public void enableButton(City city) {
    String name = city.getName();
    if (name.equals("Cairo")) {
      cairoButton.setEnabled(true);
    } else if (name.equals("Sparta")) {
      spartaButton.setEnabled(true);
    } else {
      romeButton.setEnabled(true);
    }
  }
}
