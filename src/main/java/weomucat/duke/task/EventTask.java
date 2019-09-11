package weomucat.duke.task;

import java.util.ArrayList;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidIndexException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.ui.Message;

/**
 * An event is a special task that has a date range.
 */
public class EventTask extends Task {

  private static final String DATE_RANGE_DELIMITER = "\\|";

  private DateRange at;
  private ArrayList<DateRange> atSlots;

  /**
   * Default constructor.
   *
   * @param description a description of the event
   * @param at          location of the event
   * @throws InvalidParameterException If the description is empty or at is empty.
   */
  public EventTask(String description, String at) throws InvalidParameterException {
    super(description);

    if (description.equals("")) {
      throw new InvalidParameterException("The description of an event cannot be empty.");
    }

    if (at.equals("")) {
      throw new InvalidParameterException("The date range of an event cannot be empty.");
    }

    this.atSlots = new ArrayList<>();

    String[] ranges = at.split(DATE_RANGE_DELIMITER);
    for (String range : ranges) {
      this.atSlots.add(new DateRange(range));
    }

    if (this.atSlots.size() == 1) {
      this.at = this.atSlots.get(0);
    }
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
  public Message toMessage() {
    ArrayList<String> out = new ArrayList<>();

    if (this.at != null) {
      out.add("===== AT =====");
      out.add(this.at.toString());
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
  public String toString() {
    return String.format("[E]%s", super.toString());
  }
}
