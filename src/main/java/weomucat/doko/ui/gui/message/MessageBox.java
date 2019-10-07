package weomucat.doko.ui.gui.message;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import weomucat.doko.ui.gui.Root;
import weomucat.doko.ui.message.Message;
import weomucat.doko.ui.message.element.MessageElement;

/**
 * Represents a message in Root.
 */
public class MessageBox extends HBox {

  private static final int EMPTY_BODY_PADDING = 5;
  private static final int PADDING = 10;
  private static final int BACKGROUND_RADIUS = 5;

  @FXML
  private TextFlow title;

  @FXML
  private TextFlow body;

  private Message message;
  private MessageBoxOptions titleOptions;
  private MessageBoxOptions bodyOptions;

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
    this.titleOptions = new MessageBoxOptions();
    this.bodyOptions = new MessageBoxOptions();
    updateStyle();
  }

  void setMessage(Message message) {
    this.message = message;
  }

  void setTitleOptions(MessageBoxOptions options) {
    this.titleOptions = options;
    updateStyle();
  }

  void setBodyOptions(MessageBoxOptions options) {
    this.bodyOptions = options;
    updateStyle();
  }

  private void updateStyle() {
    String bodyBackgroundRadius;
    this.title.getChildren().clear();
    if (this.message.getTitle().getElements().isEmpty()) {
      this.title.setPadding(new Insets(0));
      bodyBackgroundRadius = String.format("-fx-background-radius: %d", BACKGROUND_RADIUS);
    } else {
      this.title.setPadding(new Insets(PADDING));
      bodyBackgroundRadius = String.format("-fx-background-radius: 0 0 %d %d",
          BACKGROUND_RADIUS, BACKGROUND_RADIUS);

      for (MessageElement element : this.message.getTitle().getElements()) {
        this.title.getChildren().add(
            element.toGui(this.titleOptions));
      }
    }

    this.body.getChildren().clear();
    if (this.message.getBody().getElements().isEmpty()) {
      this.body.setPadding(new Insets(EMPTY_BODY_PADDING));
    } else {
      this.body.setPadding(new Insets(PADDING));

      for (MessageElement element : this.message.getBody().getElements()) {
        this.body.getChildren().add(
            element.toGui(this.bodyOptions));
      }
    }

    this.title.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: %d %d 0 0",
        this.titleOptions.getBackgroundColor(), BACKGROUND_RADIUS, BACKGROUND_RADIUS));
    this.body.setStyle(String.format("-fx-background-color: %s; %s",
        this.bodyOptions.getBackgroundColor(), bodyBackgroundRadius));
  }
}
