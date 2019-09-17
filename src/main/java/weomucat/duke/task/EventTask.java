package weomucat.duke.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import weomucat.duke.date.Date;
import weomucat.duke.date.DateRange;
import weomucat.duke.date.Interval;
import weomucat.duke.exception.InvalidIndexException;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageContent;
import weomucat.duke.ui.message.element.MessageText;

/**
 * An event is a special task that has a date range.
 * It can also have tentative schedules (date ranges).
 */
public class EventTask extends RecurringTask {

  private static final String NAME = "Event";

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
    List<MessageContent> contents = new ArrayList<>();

    if (this.at != null) {
      contents.add(new MessageContent()
          .addText("At: ", MessageText.Type.SECONDARY)
          .addText(this.at.toString()));
    }

    if (this.every != null) {
      contents.add(new MessageContent()
          .addText("Recurrence: ", MessageText.Type.SECONDARY)
          .addText(this.every.toString()));
    }

    if (this.atSlots.size() > 1) {
      MessageContent content = new MessageContent()
          .addText("Tentative Schedules:", MessageText.Type.SECONDARY);
      for (int i = 0; i < this.atSlots.size(); i++) {
        DateRange range = this.atSlots.get(i);

        // Format date range with no. in front
        content.addText(String.format("%d. %s", i + 1, range));
      }
      contents.add(content.insertBetween(MessageContent.newLine(1)));
    }

    return super.toMessage()
        .addBody(MessageContent.join(MessageContent.newLine(1), contents));
  }

  @Override
  public int compareTo(Date date) {
    if (this.at == null) {
      return 0;
    } else {
      return this.at.getFrom().compareTo(date);
    }
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
  String getTaskName() {
    return NAME;
  }
}
