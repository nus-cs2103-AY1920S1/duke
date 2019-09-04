package weomucat.duke.ui.gui;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a message that is sent from the user.
 * Used in {@link GraphicalUi}.
 */
public class UserText extends HBox {

  @FXML
  private Label text;

  /**
   * Creates a message that is sent from the user.
   *
   * @param text the message.
   */
  public UserText(String text) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Root.class.getResource("/view/UserText.fxml"));
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
