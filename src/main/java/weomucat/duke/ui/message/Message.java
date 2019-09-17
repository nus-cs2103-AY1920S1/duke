package weomucat.duke.ui.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a message to be displayed by a Ui.
 */
public class Message {
  private MessageContent title;
  private List<MessageContent> body;

  public Message() {
    this.title = new MessageContent();
    this.body = new ArrayList<>();
  }

  public MessageContent getTitle() {
    return this.title;
  }

  public Message setTitle(String title) {
    this.title = new MessageContent().addText(title);
    return this;
  }

  public Message setTitle(MessageContent title) {
    this.title = title;
    return this;
  }

  /**
   * Append text to be displayed in a message body.
   *
   * @param body text(s) to append
   * @return this instance
   */
  public Message addBody(String... body) {
    MessageContent[] texts = Stream.of(body)
        .map(s -> new MessageContent().addText(s))
        .toArray(MessageContent[]::new);
    this.addBody(texts);
    return this;
  }

  public Message addBody(MessageContent... body) {
    this.body.addAll(Arrays.asList(body));
    return this;
  }

  public MessageContent getBody() {
    return MessageContent.join(MessageContent.newLine(1), this.body);
  }
}
