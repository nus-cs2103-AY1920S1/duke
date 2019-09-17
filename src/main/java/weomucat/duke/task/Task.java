package weomucat.duke.task;

import java.io.Serializable;
import weomucat.duke.date.Date;
import weomucat.duke.exception.DukeRuntimeException;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageContent;

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
   * Gets the Message representation of this Task.
   *
   * @return the message
   */
  public Message toMessage() {
    MessageContent content = new MessageContent()
        .addText(this.description)
        .addTag(this.getTaskName());

    if (this.done) {
      content.addTag("Done");
    } else if (this.isOverDue()) {
      content.addTag("Overdue");
    }

    return new Message().setTitle(
        content.insertBetween(MessageContent.whitespace(1)));
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

  abstract boolean isOverDue();

  abstract String getTaskName();
}
