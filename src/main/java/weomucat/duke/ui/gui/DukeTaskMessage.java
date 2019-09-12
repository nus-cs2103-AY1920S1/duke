package weomucat.duke.ui.gui;

import javafx.geometry.Pos;
import weomucat.duke.ui.Message;

/**
 * Represents a message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
class DukeTaskMessage extends MessageBox {

  private static final String TEXT_COLOR = "#FFFFFF";
  private static final String TITLE_BACKGROUND_COLOR = "#1565c0";
  private static final String BODY_BACKGROUND_COLOR = "#0d47a1";

  /**
   * Creates a message that is sent from Duke.
   *
   * @param message the message to display.
   */
  DukeTaskMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setTitleText(message.getTitle(), TEXT_COLOR);
    setTitleBackgroundColor(TITLE_BACKGROUND_COLOR);

    setBodyText(message.getBody(), TEXT_COLOR);
    setBodyBackgroundColor(BODY_BACKGROUND_COLOR);
  }
}
