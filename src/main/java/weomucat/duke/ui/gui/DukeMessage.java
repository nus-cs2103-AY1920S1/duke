package weomucat.duke.ui.gui;

import javafx.geometry.Pos;

/**
 * Represents a message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeMessage extends Message {

  private static final String COLOR = "#FFFFFF";
  private static final String BACKGROUND_COLOR = "#37474f";

  /**
   * Creates a message that is sent from Duke.
   *
   * @param message the message.
   */
  DukeMessage(String message) {
    setAlignment(Pos.TOP_LEFT);
    setText(message);
    setTextColor(COLOR);
    setBackgroundColor(BACKGROUND_COLOR);
  }
}
