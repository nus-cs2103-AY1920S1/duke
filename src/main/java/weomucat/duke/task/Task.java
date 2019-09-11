package weomucat.duke.task;

import java.io.Serializable;
import weomucat.duke.ui.Message;

/**
 * A Task is something the user has to do. By default, a Task is not done.
 */
public abstract class Task implements Serializable {

  private String description;
  private boolean done;

  /**
   * Default constructor.
   *
   * @param description a description of the task
   */
  public Task(String description) {
    assert description != null;

    this.description = description;
  }

  /**
   * Marks the task as done or not done.
   *
   * @param done task is done or not done
   */
  public void setDone(boolean done) {
    this.done = done;
  }

  /**
   * Returns the description of this Task.
   *
   * @return description of this Task
   */
  public String getDescription() {
    return description;
  }

  public abstract Message toMessage();

  @Override
  public String toString() {
    String doneIcon = this.done ? "[✓]" : "[✘]";
    return String.format("%s %s", doneIcon, this.description);
  }
}
