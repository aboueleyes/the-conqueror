package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FxFirstExample extends Application
{
  public static void main(String[] args)
  {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage)
  {
    // Create the Text
    Text text = new Text("Hello JavaFX");
    // Create the VBox
    VBox root = new VBox();
    // Add the Text to the VBox
    root.getChildren().add(text);
    // Set the Size of the VBox
    root.setMinSize(350, 250);

    // Create the Scene
    Scene scene = new Scene(root);

    // Set the Properties of the Stage
    stage.setX(100);
    stage.setY(200);
    stage.setMinHeight(300);
    stage.setMinWidth(400);

    // Add the scene to the Stage
    stage.setScene(scene);
    // Set the title of the Stage
    stage.setTitle("Your first JavaFX Example");
    // Display the Stage
    stage.show();
  }

}