package weomucat.duke.ui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class UserText extends HBox {

  @FXML
  private Label text;

  public UserText(String text) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Root.class.getResource("/view/UserText.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.text.setText(text);
  }
}
