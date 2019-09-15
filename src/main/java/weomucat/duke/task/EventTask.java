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
    ArrayList<String> result = new ArrayList<>();

    if (this.at != null) {
      result.add(String.format("===== AT =====\n%s", this.at));
    }

    if (this.every != null) {
      result.add(String.format("===== RECURRENCE =====\n%s", this.every));
    }

    if (this.atSlots.size() > 1) {
      ArrayList<String> slots = new ArrayList<>();
      slots.add("===== TENTATIVE SCHEDULES =====");
      for (int i = 0; i < this.atSlots.size(); i++) {
        DateRange range = this.atSlots.get(i);

        // Format date range with no. in front
        slots.add(String.format("%d. %s", i + 1, range));
      }
      result.add(String.join("\n", slots));
    }

    return new Message(String.join("\n\n", result))
        .setTitle(this.toString());
  }

  @Override
  boolean isOverDue() {
    if (this.at == null) {
      return false;
    } else {
      return this.at.getTo().compareTo(Date.now()) < 0;
    }
  }

  @Override
  public String toString() {
    return String.format("[E]%s", super.toString());
  }

  @Override
  public int compareTo(Date date) {
    if (this.at == null) {
      return 0;
    } else {
      return this.at.getFrom().compareTo(date);
    }
  }
}
