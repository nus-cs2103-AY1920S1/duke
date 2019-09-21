package weomucat.doko.ui.gui.message;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Text;
import weomucat.doko.ui.message.MessageColor;
import weomucat.doko.ui.message.element.MessageTag;
import weomucat.doko.ui.message.element.MessageText;

public class MessageBoxOptions {
  private static final int TAG_PADDING = 5;
  private static final int TAG_RADIUS = 5;

  private static final MessageColor DEFAULT_PRIMARY_COLOR = new MessageColor("#000000");
  private static final MessageColor DEFAULT_SECONDARY_COLOR = new MessageColor("#336666");
  private static final MessageColor DEFAULT_BACKGROUND_COLOR = new MessageColor("#ffffff");

  private static final MessageColor DEFAULT_TAG_FOREGROUND_COLOR = new MessageColor("#000000");
  private static final MessageColor DEFAULT_TAG_BACKGROUND_COLOR = new MessageColor("#dddddd");

  private MessageColor primaryColor;
  private MessageColor secondaryColor;
  private MessageColor backgroundColor;

  private MessageColor tagForegroundColor;
  private MessageColor tagBackgroundColor;

  MessageBoxOptions() {
    this.primaryColor = DEFAULT_PRIMARY_COLOR;
    this.secondaryColor = DEFAULT_SECONDARY_COLOR;
    this.backgroundColor = DEFAULT_BACKGROUND_COLOR;

    this.tagForegroundColor = DEFAULT_TAG_FOREGROUND_COLOR;
    this.tagBackgroundColor = DEFAULT_TAG_BACKGROUND_COLOR;
  }

  /**
   * Creates a javafx Text from a MessageText.
   *
   * @param messageText the MessageText
   * @return a Text instance
   */
  public Text create(MessageText messageText) {
    String string = messageText.getText();
    MessageColor color;
    switch (messageText.getType()) {
      case PRIMARY:
        color = primaryColor;
        break;
      case SECONDARY:
        color = secondaryColor;
        break;
      default:
        color = null;
        break;
    }

    Text text = new Text(string);
    text.setFill(color.toGui());
    return text;
  }

  /**
   * Creates a javafx Label from a MessageTag.
   *
   * @param messageTag the MessageTag
   * @return a Label instance
   */
  public Label create(MessageTag messageTag) {
    Label label = new Label(messageTag.getTag());
    label.setBackground(new Background(new BackgroundFill(
        this.tagBackgroundColor.toGui(), new CornerRadii(TAG_RADIUS), null)));
    label.setPadding(new Insets(0, TAG_PADDING, 0, TAG_PADDING));
    label.setTextFill(this.tagForegroundColor.toGui());
    return label;
  }

  MessageBoxOptions setPrimaryColor(MessageColor primaryColor) {
    this.primaryColor = primaryColor;
    return this;
  }

  MessageBoxOptions setSecondaryColor(MessageColor secondaryColor) {
    this.secondaryColor = secondaryColor;
    return this;
  }

  MessageBoxOptions setTagForegroundColor(MessageColor tagForegroundColor) {
    this.tagForegroundColor = tagForegroundColor;
    return this;
  }

  MessageBoxOptions setTagBackgroundColor(MessageColor tagBackgroundColor) {
    this.tagBackgroundColor = tagBackgroundColor;
    return this;
  }

  MessageColor getBackgroundColor() {
    return backgroundColor;
  }

  MessageBoxOptions setBackgroundColor(MessageColor backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }
}
