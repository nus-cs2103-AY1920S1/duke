package weomucat.duke.ui.gui.message;

import javafx.geometry.Pos;
import weomucat.duke.ui.gui.GraphicalUi;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents a message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
public class DukeMessage extends MessageBox {

  private static final MessageColor PRIMARY_COLOR = new MessageColor("#ffffff");
  private static final MessageColor SECONDARY_COLOR = new MessageColor("#ce93d8");
  private static final MessageColor TITLE_BACKGROUND_COLOR = new MessageColor("#455a64");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#37474f");

  /**
   * Creates a message that is sent from Duke.
   *
   * @param message the message to display.
   */
  public DukeMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setTitleOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setSecondaryColor(SECONDARY_COLOR)
        .setBackgroundColor(TITLE_BACKGROUND_COLOR));
    setBodyOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setSecondaryColor(SECONDARY_COLOR)
        .setBackgroundColor(BODY_BACKGROUND_COLOR));
  }
}
