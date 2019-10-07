package weomucat.doko.ui.message.element;

import javafx.scene.Node;
import weomucat.doko.ui.gui.message.MessageBoxOptions;

public class MessageTag implements MessageElement {

  private String tag;

  public MessageTag(String tag) {
    this.tag = tag;
  }

  public String getTag() {
    return tag;
  }

  @Override
  public String toCli() {
    return String.format("[%s]", tag);
  }

  @Override
  public Node toGui(MessageBoxOptions options) {
    return options.create(this);
  }
}
