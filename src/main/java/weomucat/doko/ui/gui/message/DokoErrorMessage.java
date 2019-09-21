package weomucat.doko.ui.gui.message;

import javafx.geometry.Pos;
import weomucat.doko.ui.gui.GraphicalUi;
import weomucat.doko.ui.message.Message;
import weomucat.doko.ui.message.MessageColor;

/**
 * Represents an error message that is sent from Doko.
 * Used in {@link GraphicalUi}.
 */
public class DokoErrorMessage extends MessageBox {

  private static final MessageColor PRIMARY_COLOR = new MessageColor("#ffffff");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#880e4f");

  /**
   * Creates an error message that is sent from Doko.
   *
   * @param message the error message to display.
   */
  public DokoErrorMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setBodyOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setBackgroundColor(BODY_BACKGROUND_COLOR));
  }
}
