package weomucat.doko.ui.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import weomucat.doko.ui.message.element.MessageElement;
import weomucat.doko.ui.message.element.MessageTag;
import weomucat.doko.ui.message.element.MessageText;

public class MessageContent {

  private static final String NEW_LINE = "\n";
  private static final String WHITESPACE = " ";

  private ArrayList<MessageElement> elements;

  public MessageContent() {
    this.elements = new ArrayList<>();
  }

  /**
   * Joins a list of MessageContent into one MessageContent.
   * Similar to String.join()
   *
   * @param delimiter a MessageContent to join with
   * @param contents  the list of MessageContent
   * @return joined MessageContent
   */
  public static MessageContent join(MessageContent delimiter, List<MessageContent> contents) {
    MessageContent result = new MessageContent();

    Iterator<MessageContent> iterator = contents.iterator();
    while (iterator.hasNext()) {
      MessageContent content = iterator.next();
      result.elements.addAll(content.elements);
      if (iterator.hasNext()) {
        result.elements.addAll(delimiter.elements);
      }
    }

    return result;
  }

  public static MessageContent newLine(int n) {
    return new MessageContent().addText(NEW_LINE.repeat(n));
  }

  public static MessageContent whitespace(int n) {
    return new MessageContent().addText(WHITESPACE.repeat(n));
  }

  public MessageContent add(MessageContent content) {
    this.elements.addAll(content.elements);
    return this;
  }

  public MessageContent addText(String s) {
    this.elements.add(new MessageText(s));
    return this;
  }

  public MessageContent addText(String s, MessageText.Type type) {
    this.elements.add(new MessageText(s, type));
    return this;
  }

  public MessageContent addTag(String s) {
    this.elements.add(new MessageTag(s));
    return this;
  }

  /**
   * Inserts a delimiter between each MessageElement in this MessageContent.
   *
   * @param delimiter the delimiter
   * @return this instance
   */
  public MessageContent insertBetween(MessageContent delimiter) {
    ListIterator<MessageElement> iterator = this.elements.listIterator();
    while (iterator.hasNext()) {
      iterator.next();
      if (iterator.hasNext()) {
        for (MessageElement element : delimiter.elements) {
          iterator.add(element);
        }
      }
    }
    return this;
  }

  public List<MessageElement> getElements() {
    return elements;
  }
}
