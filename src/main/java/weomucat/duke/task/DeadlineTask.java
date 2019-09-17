package weomucat.duke.task;

import java.util.ArrayList;
import java.util.List;
import weomucat.duke.date.Date;
import weomucat.duke.date.Interval;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageContent;
import weomucat.duke.ui.message.element.MessageText;

/**
 * A deadline is a special task that has a due date.
 */
public class DeadlineTask extends RecurringTask implements SnoozableTask {

  private static final String NAME = "Deadline";

  private Date by;
  private Interval every;

  /**
   * Creates a DeadlineTask.
   *
   * @param description a description of the deadline
   * @param by          due date
   * @param every       optional. recurring task interval
   */
  public DeadlineTask(String description, Date by, Interval every) {
    super(description);
    this.by = by;
    this.every = every;
  }

  @Override
  public void removeRecurrence() {
    this.every = null;
  }

  @Override
  public RecurringTask getNextRecurrence() {
    if (this.every == null) {
      return null;
    }

    Date by = this.by.plus(this.every);
    return new DeadlineTask(getDescription(), by, this.every);
  }

  @Override
  public Message toMessage() {
    List<MessageContent> contents = new ArrayList<>();

    contents.add(new MessageContent()
        .addText("By: ", MessageText.Type.SECONDARY)
        .addText(this.by.toString()));

    if (this.every != null) {
      contents.add(new MessageContent()
          .addText("Recurrence: ", MessageText.Type.SECONDARY)
          .addText(this.every.toString()));
    }

    return super.toMessage()
        .addBody(MessageContent.join(MessageContent.newLine(1), contents));
  }

  @Override
  public int compareTo(Date date) {
    return this.by.compareTo(date);
  }

  @Override
  public void snooze(Interval interval) {
    this.by = this.by.plus(interval);
  }

  @Override
  boolean isOverDue() {
    return this.by.compareTo(Date.now()) < 0;
  }

  @Override
  String getTaskName() {
    return NAME;
  }
}
