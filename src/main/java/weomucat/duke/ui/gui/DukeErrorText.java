package weomucat.duke.ui.gui;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 * Represents an error message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
public class DukeErrorText extends Label {

  @FXML
  private Label text;

  /**
   * Creates an error message that is sent from Duke.
   *
   * @param text the error message.
   */
  public DukeErrorText(String text) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Root.class.getResource("/view/DukeErrorText.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Use UTF_8 encoding
    text = new String(text.getBytes(), UTF_8);
    this.text.setText(text);
  }
}
