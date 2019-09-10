package weomucat.duke.ui.gui;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents a text in Root.
 */
class Message extends HBox {

  private static final String BACKGROUND_COLOR = "#ffffff";
  private static final int BACKGROUND_RADIUS = 5;

  @FXML
  private Label textLabel;

  private String backgroundColor;

  /**
   * Creates a text to be added to Root.
   */
  Message() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Root.class.getResource("/view/Message.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.backgroundColor = BACKGROUND_COLOR;
    updateStyle();
  }

  /**
   * Set this label's text.
   *
   * @param text the text
   */
  void setText(String text) {
    // Use UTF_8 encoding
    text = new String(text.getBytes(), UTF_8);
    this.textLabel.setText(text);
  }

  /**
   * Set this label's text color.
   *
   * @param color the color in hexadecimal
   */
  void setTextColor(String color) {
    this.textLabel.setTextFill(Color.valueOf(color));
  }

  /**
   * Set this label's background color.
   *
   * @param color the color in hexadecimal
   */
  void setBackgroundColor(String color) {
    this.backgroundColor = color;
    updateStyle();
  }

  private void updateStyle() {
    this.textLabel.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: %d",
        this.backgroundColor, BACKGROUND_RADIUS));
  }
}
