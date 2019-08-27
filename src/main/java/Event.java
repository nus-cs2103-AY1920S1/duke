/**
 * Represents an Event Task. An event task is represented by a description and
 * the timing the event happens.
 */
public class Event extends Task {

  protected String timing;

  public Event(String description, String timing) {
    super(description);
    this.timing = timing;
  }

  /**
   * Returns the timing at which the event happens
   * 
   * @return timing
   */
  public String getTiming() {
    return this.timing;
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + " (at:" + timing + ")";
  }
}