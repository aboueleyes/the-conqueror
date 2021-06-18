package views;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.*;
import java.io.IOException;

public class BuildingPanel extends JPanel {
  private boolean built = false;
  private StyledButton build;
  private StyledLabel buildingName;

  public BuildingPanel(ActionListener a, String name) throws FontFormatException, IOException {
    setLayout(new BorderLayout());
    setBuild(new StyledButton("build", 30));
    setBuildingName(new StyledLabel(name, 25, false));
    add(new JLabel("<html><h1><strong><i>" + name + "</i></strong></h1><hr></html>"), BorderLayout.CENTER);
    add(build, BorderLayout.SOUTH);
    // add(buildingName, BorderLayout.CENTER);

  }

  public StyledLabel getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(StyledLabel buildingName) {
    this.buildingName = buildingName;
  }

  public boolean isBuilt() {
    return built;
  }

  public void setBuilt(boolean built) {
    this.built = built;
  }

  public StyledButton getBuild() {
    return build;
  }

  public void setBuild(StyledButton build) {
    this.build = build;
  }

}