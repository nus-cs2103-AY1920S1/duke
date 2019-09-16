package weomucat.duke.ui.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.text.Text;

public class MessageText {

  private static final String NEW_LINE = "\n";

  private String text;
  private MessageColor color;
  private MessageColor backgroundColor;

  public MessageText(String text) {
    this.text = text;
  }

  public static List<MessageText> join(MessageText delimiter, List<List<MessageText>> texts) {
    ArrayList<MessageText> result = new ArrayList<>();

    Iterator<List<MessageText>> textIterator = texts.iterator();
    while (textIterator.hasNext()) {
      List<MessageText> text = textIterator.next();
      result.addAll(text);
      if (textIterator.hasNext()) {
        result.add(delimiter);
      }
    }

    return result;
  }

  public static MessageText newLine(int n) {
    return new MessageText(NEW_LINE.repeat(n));
  }

  public String toCli() {
    return this.text;
  }

  public Text toGui() {
    return new Text(this.text);
  }

  public MessageColor getColor() {
    return color;
  }

  public MessageText setColor(String color) {
    this.color = new MessageColor(color);
    return this;
  }

  public MessageColor getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = new MessageColor(backgroundColor);
  }
}
