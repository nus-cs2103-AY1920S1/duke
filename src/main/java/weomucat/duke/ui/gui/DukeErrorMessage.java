package weomucat.duke.ui.gui;

import javafx.geometry.Pos;

/**
 * Represents an error message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeErrorMessage extends Message {

  private static final String COLOR = "#FFFFFF";
  private static final String BACKGROUND_COLOR = "#880e4f";

  /**
   * Creates an error message that is sent from Duke.
   *
   * @param message the error message.
   */
  DukeErrorMessage(String message) {
    setAlignment(Pos.TOP_LEFT);
    setText(message);
    setTextColor(COLOR);
    setBackgroundColor(BACKGROUND_COLOR);
  }
}
