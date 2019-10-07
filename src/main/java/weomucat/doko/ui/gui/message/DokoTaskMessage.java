package weomucat.doko.ui.gui.message;

import javafx.geometry.Pos;
import weomucat.doko.ui.gui.GraphicalUi;
import weomucat.doko.ui.message.Message;
import weomucat.doko.ui.message.MessageColor;

/**
 * Represents a message that is sent from Doko.
 * Used in {@link GraphicalUi}.
 */
public class DokoTaskMessage extends MessageBox {

  private static final MessageColor PRIMARY_COLOR = new MessageColor("#ffffff");
  private static final MessageColor SECONDARY_COLOR = new MessageColor("#fbc02d");
  private static final MessageColor TITLE_BACKGROUND_COLOR = new MessageColor("#1565c0");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#0d47a1");

  private static final MessageColor TAG_FOREGROUND_COLOR = new MessageColor("#ffffff");
  private static final MessageColor TAG_BACKGROUND_COLOR = new MessageColor("#0097a7");

  /**
   * Creates a message that is sent from Doko.
   *
   * @param message the message to display.
   */
  public DokoTaskMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setTitleOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setSecondaryColor(SECONDARY_COLOR)
        .setBackgroundColor(TITLE_BACKGROUND_COLOR)
        .setTagForegroundColor(TAG_FOREGROUND_COLOR)
        .setTagBackgroundColor(TAG_BACKGROUND_COLOR));
    setBodyOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setSecondaryColor(SECONDARY_COLOR)
        .setBackgroundColor(BODY_BACKGROUND_COLOR));
  }
}
