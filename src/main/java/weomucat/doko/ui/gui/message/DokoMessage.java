package weomucat.doko.ui.gui.message;

import javafx.geometry.Pos;
import weomucat.doko.ui.gui.GraphicalUi;
import weomucat.doko.ui.message.Message;
import weomucat.doko.ui.message.MessageColor;

/**
 * Represents a message that is sent from Doko.
 * Used in {@link GraphicalUi}.
 */
public class DokoMessage extends MessageBox {

  private static final MessageColor PRIMARY_COLOR = new MessageColor("#ffffff");
  private static final MessageColor SECONDARY_COLOR = new MessageColor("#ce93d8");
  private static final MessageColor TITLE_BACKGROUND_COLOR = new MessageColor("#455a64");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#37474f");

  /**
   * Creates a message that is sent from Doko.
   *
   * @param message the message to display.
   */
  public DokoMessage(Message message) {
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
