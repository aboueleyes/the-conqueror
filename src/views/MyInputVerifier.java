package views;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;

public class MyInputVerifier extends InputVerifier {
  public static final int MAX_NAME_SIZE = 35;

  @Override
  public boolean verify(JComponent input) {
    String text = ((JTextField) input).getText();
    if (text.length() > MAX_NAME_SIZE) {
      showMessageDialog(null, "Name is TOO long");
      return false;
    }
    if (text.isBlank()) {
      showMessageDialog(null, "Name cannot Blank ");
      return false;
    }
    return true;
  }

}
