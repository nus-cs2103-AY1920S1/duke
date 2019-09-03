package weomucat.duke.ui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public class DukeErrorText extends Label {

  @FXML
  private Label text;

  public DukeErrorText(String text) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Root.class.getResource("/view/DukeErrorText.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.text.setText(text);
  }
}
