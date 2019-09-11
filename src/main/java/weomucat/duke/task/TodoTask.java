package weomucat.duke.task;

import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.ui.Message;

/**
 * Todo is a special task that must have a description.
 */
public class TodoTask extends Task {

  /**
   * Default constructor.
   *
   * @param description a description of the todo
   * @throws InvalidParameterException If the description is empty.
   */
  public TodoTask(String description) throws InvalidParameterException {
    super(description);

    if (description.equals("")) {
      throw new InvalidParameterException("The description of a todo cannot be empty.");
    }
  }

  @Override
  public Message toMessage() {
    return new Message()
        .setTitle(this.toString());
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }
}
