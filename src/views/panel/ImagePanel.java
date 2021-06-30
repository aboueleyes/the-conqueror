package views.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

  private Image img;

  public Image getImg() {
    return img;
  }

  public void setImg(Image img) {
    this.img = img;
  }

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
    setLayout(new BorderLayout());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(new Dimension(50, 50));
    setMaximumSize(size);
    setSize(size);

  }

  @Override
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

  public static void main(String[] args) {
    JFrame test = new JFrame();
    test.setVisible(true);
    ImagePanel panel = new ImagePanel(new ImageIcon("./assets/img/archer.jpg").getImage());
    test.setLayout(new BorderLayout());
    JPanel p = new JPanel();
    p.add(panel);
    panel.setLayout(new BorderLayout());
    panel.add(new JLabel("fhif"), BorderLayout.PAGE_END);
    test.add(panel);
    test.add(panel);
  }
}
