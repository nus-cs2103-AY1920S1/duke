package duke.task;

/**
 * Event class represents a meeting a user wants to attend at a specified date.
 *
 * @author scwaterbear
 */
public class Event extends Task {

  private String at;

  /**
   * Class constructor.
   *
   * @param description description of event.
   * @param at          event date and time.
   */
  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  /**
   * Class constructor.
   *
   * @param description description of event.
   * @param at          event date and time.
   * @param isDone      status of event.
   */
  public Event(String description, String at, boolean isDone) {
    super(description, isDone);
    this.at = at;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + at + ")";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toDataFormat() {
    return "E" + super.toDataFormat() + " | " + at;
  }
}
