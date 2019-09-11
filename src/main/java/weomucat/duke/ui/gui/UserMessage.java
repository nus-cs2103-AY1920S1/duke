package weomucat.duke.ui.gui;

import javafx.geometry.Pos;

/**
 * Represents a message that is sent from the user.
 * Used in {@link GraphicalUi}.
 */
class UserMessage extends MessageBox {

  private static final String TEXT_COLOR = "#000000";
  private static final String BACKGROUND_COLOR = "#ffffff";

  /**
   * Creates a message that is sent from the user.
   *
   * @param message the message.
   */
  UserMessage(String message) {
    setAlignment(Pos.TOP_RIGHT);
    setBodyText(message, TEXT_COLOR);
    setBodyBackgroundColor(BACKGROUND_COLOR);
  }
}
