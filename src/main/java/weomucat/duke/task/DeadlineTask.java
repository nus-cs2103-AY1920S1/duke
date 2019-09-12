package weomucat.duke.task;

import java.time.Duration;
import java.util.ArrayList;
import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.ui.Message;

/**
 * A deadline is a special task that has a due date.
 */
public class DeadlineTask extends Task implements SnoozableTask {

  private Date by;

  /**
   * Default constructor.
   *
   * @param description a description of the deadline
   * @param by          date due. format of the date is DATETIME_PARSE_PATTERN
   * @throws InvalidParameterException If the description is empty, by is empty or by is invalid.
   */
  public DeadlineTask(String description, String by) throws InvalidParameterException {
    super(description);

    if (description.equals("")) {
      throw new InvalidParameterException("The description of a deadline cannot be empty.");
    }

    if (by.equals("")) {
      throw new InvalidParameterException("The due date of a deadline cannot be empty.");
    }

    // Parse 'by' into a ZonedDateTime object.
    this.by = new Date(by);
  }

  @Override
  public Message toMessage() {
    ArrayList<String> out = new ArrayList<>();
    out.add("===== BY =====");
    out.add(this.by.toString());

    return new Message(out.toArray(new String[0]))
        .setTitle(this.toString());
  }

  @Override
  public String toString() {
    return String.format("[D]%s", super.toString());
  }

  @Override
  public void snooze(Duration duration) {
    this.by.plus(duration);
  }
}
