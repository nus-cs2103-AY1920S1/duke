package weomucat.duke.ui.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a message to be displayed by a Ui.
 */
public class Message {
  private List<MessageText> title;
  private List<MessageText> body;

  public Message() {
    this.title = new ArrayList<>();
    this.body = new ArrayList<>();
  }

  public Message addTitle(String string) {
    this.title.add(new MessageText(string));
    return this;
  }

  public Message addTitle(MessageText text) {
    this.title.add(text);
    return this;
  }

  public List<MessageText> getTitle() {
    return this.title;
  }

  public Message addBody(String... body) {
    List<List<MessageText>> texts = Stream.of(body)
        .map(MessageText::new)
        .map(Arrays::asList)
        .collect(Collectors.toList());
    addBody(texts);
    return this;
  }

  public Message addBody(List<List<MessageText>> body) {
    this.body.addAll(MessageText.join(MessageText.newLine(1), body));
    return this;
  }

  public List<MessageText> getBody() {
    return this.body;
  }
}
