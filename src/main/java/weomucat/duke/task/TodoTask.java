package weomucat.duke.task;

import weomucat.duke.date.Date;
import weomucat.duke.ui.message.Message;

/**
 * Todo is a special task that must have a description.
 */
public class TodoTask extends Task {

  /**
   * Creates a TodoTask.
   *
   * @param description a description of the todo
   */
  public TodoTask(String description) {
    super(description);
  }

  @Override
  public Message toMessage() {
    return new Message()
        .addTitle(this.toString());
  }

  @Override
  boolean isOverDue() {
    return false;
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }

  @Override
  public int compareTo(Date date) {
    return 0;
  }
}
