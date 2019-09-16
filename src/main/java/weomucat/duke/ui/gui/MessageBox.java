package weomucat.duke.ui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;
import weomucat.duke.ui.message.MessageText;

/**
 * Represents a message in Root.
 */
class MessageBox extends HBox {

  private static final String FONT_NAME = "Monospaced";
  private static final int FONT_SIZE = 16;
  private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;

  private static final MessageColor DEFAULT_TEXT_COLOR = new MessageColor("#000000");
  private static final MessageColor DEFAULT_BACKGROUND_COLOR = new MessageColor("#ffffff");

  private static final int EMPTY_BODY_PADDING = 5;
  private static final int PADDING = 10;
  private static final int BACKGROUND_RADIUS = 5;

  @FXML
  private TextFlow title;

  @FXML
  private TextFlow body;

  private Message message;
  private MessageColor titleTextColor;
  private MessageColor titleBackgroundColor;
  private MessageColor bodyTextColor;
  private MessageColor bodyBackgroundColor;

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

    this.message = new Message();
    this.titleTextColor = DEFAULT_TEXT_COLOR;
    this.titleBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    this.bodyTextColor = DEFAULT_TEXT_COLOR;
    this.bodyBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    updateStyle();
  }

  void setMessage(Message message) {
    this.message = message;
  }

  /**
   * Set this title's text color.
   *
   * @param color the color in hexadecimal
   */
  void setTitleTextColor(MessageColor color) {
    this.titleTextColor = color;
    updateStyle();
  }

  /**
   * Set this title's background color.
   *
   * @param color the color in hexadecimal
   */
  void setTitleBackgroundColor(MessageColor color) {
    this.titleBackgroundColor = color;
    updateStyle();
  }

  /**
   * Set this body's text color.
   *
   * @param color the color in hexadecimal
   */
  void setBodyTextColor(MessageColor color) {
    this.bodyTextColor = color;
    updateStyle();
  }

  /**
   * Set this body's background color.
   *
   * @param color the color in hexadecimal
   */
  void setBodyBackgroundColor(MessageColor color) {
    this.bodyBackgroundColor = color;
    updateStyle();
  }

  private void updateStyle() {
    String bodyBackgroundRadius;
    this.title.getChildren().clear();
    if (this.message.getTitle().size() == 0) {
      this.title.setPadding(new Insets(0));
      bodyBackgroundRadius = String.format("-fx-background-radius: %d", BACKGROUND_RADIUS);
    } else {
      this.title.setPadding(new Insets(PADDING));
      bodyBackgroundRadius = String.format("-fx-background-radius: 0 0 %d %d",
          BACKGROUND_RADIUS, BACKGROUND_RADIUS);

      for (MessageText text : this.message.getTitle()) {
        Text node = text.toGui();
        MessageColor color = text.getColor();
        if (color == null) {
          node.setFill(this.titleTextColor.toGui());
        } else {
          node.setFill(color.toGui());
        }
        node.setFont(Font.font(FONT_NAME, FONT_WEIGHT, FONT_SIZE));
        this.title.getChildren().add(node);
      }
    }

    this.body.getChildren().clear();
    if (this.message.getBody().size() == 0) {
      this.body.setPadding(new Insets(EMPTY_BODY_PADDING));
    } else {
      this.body.setPadding(new Insets(PADDING));

      for (MessageText text : this.message.getBody()) {
        Text node = text.toGui();
        MessageColor color = text.getColor();
        if (color == null) {
          node.setFill(this.bodyTextColor.toGui());
        } else {
          node.setFill(color.toGui());
        }
        node.setFont(Font.font(FONT_NAME, FONT_WEIGHT, FONT_SIZE));
        this.body.getChildren().add(node);
      }
    }

    this.title.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: %d %d 0 0",
        this.titleBackgroundColor, BACKGROUND_RADIUS, BACKGROUND_RADIUS));
    this.body.setStyle(String.format("-fx-background-color: %s; %s",
        this.bodyBackgroundColor, bodyBackgroundRadius));
  }
}
