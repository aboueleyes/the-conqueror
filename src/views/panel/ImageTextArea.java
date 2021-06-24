package views.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ImageTextArea extends JTextArea {
	
	private Image backgroundImage;

  public ImageTextArea(String path) {
      super();
      setEditable(false);
      this.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 20));
      setOpaque(false);
      this.backgroundImage = new ImageIcon(path).getImage();

  }
  
  @Override
  protected void paintComponent(Graphics g) {
      g.setColor(getBackground());
      g.fillRect(0, 0, getWidth(), getHeight());
      if (backgroundImage != null) {
          g.drawImage(backgroundImage, 0, 0, this);
      }
      super.paintComponent(g);
      this.repaint();
  }
  
  public static void main(String[] args) {
  	JFrame test = new JFrame();
  	test.setLayout(new BorderLayout());
  	test.setVisible(true);
  	ImageTextArea area = new ImageTextArea("src/images/battle3.jpg");
  	area.setText("test");
  	test.add(area, BorderLayout.CENTER);
  	test.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }
  
}
