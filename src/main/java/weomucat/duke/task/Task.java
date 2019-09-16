package weomucat.duke.task;

import java.io.Serializable;
import weomucat.duke.date.Date;
import weomucat.duke.exception.DukeRuntimeException;
import weomucat.duke.ui.message.Message;

/**
 * A Task is something the user has to do. By default, a Task is not done.
 */
public abstract class Task implements Comparable<Date>, Serializable {

  private String description;
  private boolean done;

  /**
   * Creates a task with a description.
   * Task description should not be empty.
   *
   * @param description a description of the task
   */
  Task(String description) {
    assert description != null;

    if (description.equals("")) {
      throw new DukeRuntimeException("Task description should not be empty.");
    }

    this.description = description;
  }

  /**
   * Returns the description of this Task.
   *
   * @return description of this Task
   */
  public String getDescription() {
    return description;
  }

  public boolean isDone() {
    return this.done;
  }

  /**
   * Marks the task as done or not done.
   *
   * @param done task is done or not done
   */
  public void setDone(boolean done) {
    this.done = done;
  }

  public abstract Message toMessage();

  abstract boolean isOverDue();

  @Override
  public String toString() {
    String doneIcon = this.done ? "[✓]" : "[✘]";
    return String.format("%s %s", doneIcon, this.description);
  }
}
