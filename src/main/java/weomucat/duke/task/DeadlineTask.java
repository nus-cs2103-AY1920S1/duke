package weomucat.duke.task;

import java.time.Duration;
import java.util.ArrayList;
import weomucat.duke.date.Date;
import weomucat.duke.ui.Message;

/**
 * A deadline is a special task that has a due date.
 */
public class DeadlineTask extends RecurringTask implements SnoozableTask {

  private Date by;
  private Duration every;

  /**
   * Creates a DeadlineTask.
   *
   * @param description a description of the deadline
   * @param by          due date
   * @param every       optional. recurring task interval
   */
  public DeadlineTask(String description, Date by, Duration every) {
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
    ArrayList<String> out = new ArrayList<>();
    out.add("===== BY =====");
    out.add(this.by.toString());

    if (this.every != null) {
      out.add("===== RECURRENCE =====");
      out.add(this.every.toString());
    }

    return new Message(out.toArray(new String[0]))
        .setTitle(this.toString());
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
  public void snooze(Duration duration) {
    this.by = this.by.plus(duration);
  }
}
