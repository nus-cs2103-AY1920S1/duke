package weomucat.duke.task;

import java.io.Serializable;
import java.util.UUID;
import weomucat.duke.ui.Message;

/**
 * A Task is something the user has to do. By default, a Task is not done.
 */
public abstract class Task implements Serializable {

  private String description;
  private UUID uuid;
  private boolean done;

  /**
   * Default constructor.
   *
   * @param description a description of the task
   */
  public Task(String description) {
    assert description != null;

    this.description = description;
    this.uuid = UUID.randomUUID();
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

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public abstract Message toMessage();

  @Override
  public String toString() {
    String doneIcon = this.done ? "[✓]" : "[✘]";
    return String.format("%s %s", doneIcon, this.description);
  }
}
