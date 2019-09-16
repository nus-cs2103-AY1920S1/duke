package weomucat.duke.ui.gui;

import javafx.geometry.Pos;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents an error message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeErrorMessage extends MessageBox {

  private static final MessageColor TEXT_COLOR = new MessageColor("#ffffff");
  private static final MessageColor BACKGROUND_COLOR = new MessageColor("#880e4f");

  /**
   * Creates an error message that is sent from Duke.
   *
   * @param message the error message to display.
   */
  DukeErrorMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setBodyTextColor(TEXT_COLOR);
    setBodyBackgroundColor(BACKGROUND_COLOR);
  }
}
