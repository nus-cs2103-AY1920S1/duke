package weomucat.duke.task;

import java.io.Serializable;

/**
 * A Task is something the user has to do. By default, a Task is not done.
 */
public abstract class Task implements Serializable {

  private String description;
  private boolean done;

  /**
   * @param description a description of the task
   */
  public Task(String description) {
    this.description = description;
  }

  /**
   * @param done marks the task as done or not done
   */
  public void setDone(boolean done) {
    this.done = done;
  }

  /**
   * @return description of this Task
   */
  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    String done_icon = this.done ? "[\u2713]" : "[\u2718]";
    return String.format("%s %s", done_icon, this.description);
  }
}
