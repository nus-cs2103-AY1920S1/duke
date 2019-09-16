package weomucat.duke.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents a message that is sent from the user.
 * Used in {@link GraphicalUi}.
 */
class UserMessage extends MessageBox {

  private static final MessageColor TEXT_COLOR = new MessageColor("#000000");
  private static final MessageColor BACKGROUND_COLOR = new MessageColor("#ffffff");

  /**
   * Creates a message that is sent from the user.
   *
   * @param message the message.
   */
  UserMessage(String message) {
    setAlignment(Pos.TOP_RIGHT);
    setMessage(new Message().addBody(message));
    setBodyTextColor(TEXT_COLOR);
    setBodyBackgroundColor(BACKGROUND_COLOR);
  }
}
