package weomucat.duke.ui.gui;

import javafx.geometry.Pos;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents a message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeMessage extends MessageBox {

  private static final MessageColor TEXT_COLOR = new MessageColor("#ffffff");
  private static final MessageColor TITLE_BACKGROUND_COLOR = new MessageColor("#455a64");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#37474f");

  /**
   * Creates a message that is sent from Duke.
   *
   * @param message the message to display.
   */
  DukeMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setTitleTextColor(TEXT_COLOR);
    setTitleBackgroundColor(TITLE_BACKGROUND_COLOR);

    setBodyTextColor(TEXT_COLOR);
    setBodyBackgroundColor(BODY_BACKGROUND_COLOR);
  }
}
