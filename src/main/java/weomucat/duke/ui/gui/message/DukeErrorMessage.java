package weomucat.duke.ui.gui.message;

import javafx.geometry.Pos;
import weomucat.duke.ui.gui.GraphicalUi;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents an error message that is sent from Duke.
 * Used in {@link GraphicalUi}.
 */
public class DukeErrorMessage extends MessageBox {

  private static final MessageColor PRIMARY_COLOR = new MessageColor("#ffffff");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#880e4f");

  /**
   * Creates an error message that is sent from Duke.
   *
   * @param message the error message to display.
   */
  public DukeErrorMessage(Message message) {
    super();
    setAlignment(Pos.TOP_LEFT);
    setMessage(message);
    setBodyOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setBackgroundColor(BODY_BACKGROUND_COLOR));
  }
}
