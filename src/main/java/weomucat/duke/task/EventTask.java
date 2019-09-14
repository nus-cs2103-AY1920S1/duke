package weomucat.duke.task;

import java.util.ArrayList;
import java.util.Collection;
import weomucat.duke.date.Date;
import weomucat.duke.date.DateRange;
import weomucat.duke.date.Interval;
import weomucat.duke.exception.InvalidIndexException;
import weomucat.duke.ui.Message;

/**
 * An event is a special task that has a date range.
 * It can also have tentative schedules (date ranges).
 */
public class EventTask extends RecurringTask {

  private static final String DATE_RANGE_DELIMITER = "\\|";

  private DateRange at;
  private ArrayList<DateRange> atSlots;
  private Interval every;

  /**
   * Creates an EventTask.
   *
   * @param description a description of the event
   * @param at          date range(s) of the event
   * @param every       optional. recurring task interval
   */
  public EventTask(String description, Collection<DateRange> at, Interval every) {
    super(description);

    this.atSlots = new ArrayList<>(at);
    if (this.atSlots.size() == 1) {
      this.at = this.atSlots.get(0);
    }
    this.every = every;
  }

  /**
   * Set the schedule of this event.
   *
   * @param i an index from the list of tentative schedules
   * @throws InvalidIndexException if the index is invalid
   */
  public void setAt(int i) throws InvalidIndexException {
    try {
      this.at = this.atSlots.get(i);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException("That is not a valid index of a schedule.");
    }
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

    ArrayList<DateRange> at = new ArrayList<>();
    for (DateRange slot : this.atSlots) {
      at.add(slot.plus(this.every));
    }
    return new EventTask(getDescription(), at, this.every);
  }

  @Override
  public Message toMessage() {
    ArrayList<String> out = new ArrayList<>();

    if (this.at != null) {
      out.add("===== AT =====");
      out.add(this.at.toString());
    }

    if (this.every != null) {
      out.add("===== RECURRENCE =====");
      out.add(this.every.toString());
    }

    if (this.atSlots.size() > 1) {
      out.add("===== TENTATIVE SCHEDULES =====");
      for (int i = 0; i < this.atSlots.size(); i++) {
        DateRange range = this.atSlots.get(i);

        // Format date range with no. in front
        out.add(String.format("%d. %s", i + 1, range));
      }
    }

    return new Message(out.toArray(new String[0]))
        .setTitle(this.toString());
  }

  @Override
  boolean isOverDue() {
    return this.at.getTo().compareTo(Date.now()) < 0;
  }

  @Override
  public String toString() {
    return String.format("[E]%s", super.toString());
  }

  @Override
  public int compareTo(Date date) {
    return this.at.getFrom().compareTo(date);
  }
}
