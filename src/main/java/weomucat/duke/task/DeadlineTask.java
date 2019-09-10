package weomucat.duke.task;

import java.time.ZonedDateTime;
import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;

/**
 * A deadline is a special task that has a due date.
 */
public class DeadlineTask extends Task {

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
      throw new InvalidParameterException("The date of a deadline cannot be empty.");
    }

    // Parse 'by' into a ZonedDateTime object.
    this.by = new Date(by);
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.by);
  }
}
