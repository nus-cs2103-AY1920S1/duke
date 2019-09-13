package weomucat.duke.task;

import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.ui.Message;

/**
 * Todo is a special task that must have a description.
 */
public class TodoTask extends Task {

  private TodoTask(String description) {
    super(description);
  }

  /**
   * Creates a TodoTask.
   *
   * @param description a description of the todo
   * @throws InvalidParameterException if the description is empty.
   */
  public static TodoTask create(String description) throws InvalidParameterException {
    if (description.equals("")) {
      throw new InvalidParameterException("The description of a todo cannot be empty.");
    }

    return new TodoTask(description);
  }

  @Override
  public Message toMessage() {
    return new Message()
        .setTitle(this.toString());
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
