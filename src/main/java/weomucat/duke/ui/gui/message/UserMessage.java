package weomucat.duke.ui.gui.message;

import javafx.geometry.Pos;
import weomucat.duke.ui.gui.GraphicalUi;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageColor;

/**
 * Represents a message that is sent from the user.
 * Used in {@link GraphicalUi}.
 */
public class UserMessage extends MessageBox {

  private static final MessageColor PRIMARY_COLOR = new MessageColor("#000000");
  private static final MessageColor BODY_BACKGROUND_COLOR = new MessageColor("#ffffff");

  /**
   * Creates a message that is sent from the user.
   *
   * @param message the message.
   */
  public UserMessage(String message) {
    setAlignment(Pos.TOP_RIGHT);
    setMessage(new Message().addBody(message));
    setBodyOptions(new MessageBoxOptions()
        .setPrimaryColor(PRIMARY_COLOR)
        .setBackgroundColor(BODY_BACKGROUND_COLOR));
  }
}
