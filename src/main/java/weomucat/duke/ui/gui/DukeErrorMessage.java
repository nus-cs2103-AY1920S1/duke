package weomucat.duke.ui.gui;

import javafx.geometry.Pos;
import weomucat.duke.ui.Message;

/**
 * Represents an error message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeErrorMessage extends MessageBox {

  private static final String TEXT_COLOR = "#FFFFFF";
  private static final String BACKGROUND_COLOR = "#880e4f";

  /**
   * Creates an error message that is sent from Duke.
   *
   * @param message the error message to display.
   */
  DukeErrorMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setBodyText(message.getBody(), TEXT_COLOR);
    setBodyBackgroundColor(BACKGROUND_COLOR);
  }
}
