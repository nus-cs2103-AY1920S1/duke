package weomucat.duke.task;

import java.io.Serializable;

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
   * The description of this Task.
   *
   * @return description of this Task
   */
  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    String doneIcon = this.done ? "[✓]" : "[✘]";
    return String.format("%s %s", doneIcon, this.description);
  }
}
