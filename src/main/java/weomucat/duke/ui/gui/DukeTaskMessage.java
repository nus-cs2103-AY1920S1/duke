package weomucat.duke.ui.gui;

import javafx.geometry.Pos;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents a message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeTaskMessage extends MessageBox {

  private static final MessageColor TEXT_COLOR = new MessageColor("#ffffff");
  private static final MessageColor TITLE_BACKGROUND_COLOR = new MessageColor("#1565c0");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#0d47a1");

  /**
   * Creates a message that is sent from Duke.
   *
   * @param message the message to display.
   */
  DukeTaskMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setTitleTextColor(TEXT_COLOR);
    setTitleBackgroundColor(TITLE_BACKGROUND_COLOR);

    setBodyTextColor(TEXT_COLOR);
    setBodyBackgroundColor(BODY_BACKGROUND_COLOR);
  }
}
