package weomucat.duke.ui.gui;

import javafx.geometry.Pos;

/**
 * Represents a message that is sent from the user.
 * Used in {@link GraphicalUi}.
 */
class UserMessage extends Message {

  private static final String COLOR = "#000000";
  private static final String BACKGROUND_COLOR = "#ffffff";

  /**
   * Creates a message that is sent from the user.
   *
   * @param message the message.
   */
  UserMessage(String message) {
    setAlignment(Pos.TOP_RIGHT);
    setText(message);
    setTextColor(COLOR);
    setBackgroundColor(BACKGROUND_COLOR);
  }
}
