package weomucat.doko.ui.message.element;

import javafx.scene.text.Text;
import weomucat.doko.ui.gui.message.MessageBoxOptions;

public class MessageText implements MessageElement {

  private String text;
  private Type type;

  public MessageText(String text) {
    this(text, Type.PRIMARY);
  }

  public MessageText(String text, Type type) {
    this.text = text;
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public Type getType() {
    return type;
  }

  @Override
  public String toCli() {
    return this.text;
  }

  @Override
  public Text toGui(MessageBoxOptions options) {
    return options.create(this);
  }

  public enum Type {
    PRIMARY, SECONDARY
  }
}
