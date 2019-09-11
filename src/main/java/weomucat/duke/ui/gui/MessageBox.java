package weomucat.duke.ui.gui;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a message in Root.
 */
class MessageBox extends HBox {

  private static final String DEFAULT_TEXT = "";
  private static final String DEFAULT_BACKGROUND_COLOR = "#ffffff";

  private static final int PADDING = 10;
  private static final int BACKGROUND_RADIUS = 5;

  @FXML
  private TextFlow title;

  @FXML
  private TextFlow body;

  private String titleText;
  private String titleColor;
  private String titleBackgroundColor;

  private String bodyText;
  private String bodyBackgroundColor;

  /**
   * Creates a text to be added to Root.
   */
  MessageBox() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Root.class.getResource("/view/MessageBox.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.titleText = DEFAULT_TEXT;
    this.titleBackgroundColor = DEFAULT_BACKGROUND_COLOR;

    this.bodyText = DEFAULT_TEXT;
    this.bodyBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    updateStyle();
  }

  /**
   * Set this title's text.
   *
   * @param text the text
   */
  void setTitleText(String text, String color) {
    // Use UTF_8 encoding
    this.titleText = new String(text.getBytes(), UTF_8);
    this.titleColor = color;
    updateStyle();
  }

  /**
   * Set this title's text color.
   *
   * @param color the color in hexadecimal
   */
  void setTitleBackgroundColor(String color) {
    this.titleBackgroundColor = color;
    updateStyle();
  }


  /**
   * Add to this body's text.
   *
   * @param text  the text
   * @param color the text color
   */
  void setBodyText(String text, String color) {
    // Use UTF_8 encoding
    this.bodyText = new String(text.getBytes(), UTF_8);

    this.body.setPadding(new Insets(PADDING));

    Text textNode = new Text(this.bodyText);
    textNode.setFill(Color.valueOf(color));
    textNode.setFont(Font.font("Monospaced", FontWeight.BOLD, 14));

    this.body.getChildren().clear();
    this.body.getChildren().add(textNode);
  }

  /**
   * Set this body's background color.
   *
   * @param color the color in hexadecimal
   */
  void setBodyBackgroundColor(String color) {
    this.bodyBackgroundColor = color;
    updateStyle();
  }

  private void updateStyle() {
    String bodyBackgroundRadius;
    if (this.titleText.equals(DEFAULT_TEXT)) {
      bodyBackgroundRadius = String.format("-fx-background-radius: %d", BACKGROUND_RADIUS);
    } else {
      this.title.setPadding(new Insets(PADDING));

      Text textNode = new Text(this.titleText);
      textNode.setFill(Color.valueOf(titleColor));
      textNode.setFont(Font.font("Monospaced", FontWeight.BOLD, 14));

      this.title.getChildren().clear();
      this.title.getChildren().add(textNode);

      bodyBackgroundRadius = String.format("-fx-background-radius: 0 0 %d %d",
          BACKGROUND_RADIUS, BACKGROUND_RADIUS);
    }

    this.title.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: %d %d 0 0",
        this.titleBackgroundColor, BACKGROUND_RADIUS, BACKGROUND_RADIUS));
    this.body.setStyle(String.format("-fx-background-color: %s; %s",
        this.bodyBackgroundColor, bodyBackgroundRadius));
  }
}
