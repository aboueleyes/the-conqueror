package controllers;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;

public class MyInputVerifier extends InputVerifier {
  public static final int MAX_NAME_SIZE = 20;

  @Override
  public boolean verify(JComponent input) {
    String text = ((JTextField) input).getText();
    if (text.length() > MAX_NAME_SIZE) {
      showMessageDialog(null, "ERROR");
      return false;
    }
    if (text.isBlank()) {
      showMessageDialog(null, "ERROR");
      return false;
    }
    return true;
  }

}
