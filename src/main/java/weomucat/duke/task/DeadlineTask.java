package weomucat.duke.task;

import java.util.ArrayList;
import weomucat.duke.date.Date;
import weomucat.duke.date.Interval;
import weomucat.duke.ui.message.Message;

/**
 * A deadline is a special task that has a due date.
 */
public class DeadlineTask extends RecurringTask implements SnoozableTask {

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
    ArrayList<String> result = new ArrayList<>();
    result.add(String.format("===== BY =====\n%s", this.by));

    if (this.every != null) {
      result.add(String.format("===== RECURRENCE =====\n%s", this.every));
    }

    return new Message()
        .addTitle(this.toString())
        .addBody(String.join("\n\n", result));
  }

  @Override
  boolean isOverDue() {
    return this.by.compareTo(Date.now()) < 0;
  }

  @Override
  public String toString() {
    return String.format("[D]%s", super.toString());
  }

  @Override
  public int compareTo(Date date) {
    return this.by.compareTo(date);
  }

  @Override
  public void snooze(Interval interval) {
    this.by = this.by.plus(interval);
  }
}
