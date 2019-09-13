package weomucat.duke.task;

import java.time.Duration;

public abstract class RecurringTask extends Task {

  /**
   * Creates a recurring task with a description.
   *
   * @param description a description of the task
   */
  public RecurringTask(String description) {
    super(description);
  }

  abstract void setRecurrence(Duration duration);

  abstract RecurringTask getNextRecurrence();
}
