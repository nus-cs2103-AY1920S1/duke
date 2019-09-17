package weomucat.duke.task;

import weomucat.duke.date.Date;

/**
 * Todo is a special task that must have a description.
 */
public class TodoTask extends Task {

  private static final String NAME = "Todo";

  /**
   * Creates a TodoTask.
   *
   * @param description a description of the todo
   */
  public TodoTask(String description) {
    super(description);
  }

  @Override
  public int compareTo(Date date) {
    return 0;
  }

  @Override
  boolean isOverDue() {
    return false;
  }

  @Override
  String getTaskName() {
    return NAME;
  }
}
