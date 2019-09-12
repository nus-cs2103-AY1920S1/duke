package weomucat.duke.ui;

/**
 * Represents a message to be displayed by a Ui.
 */
public class Message {
  private String title;
  private String[] body;

  /**
   * Creates a Message.
   *
   * @param body use varargs for multiline messages
   */
  public Message(String... body) {
    this.title = "";
    this.body = body;
  }

  public String getTitle() {
    return title;
  }

  public Message setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getBody() {
    return String.join("\n", body);
  }
}
