public class Event extends Task {

  private String timing;

  // Constructor
  public Event(String description, String timing) {
    super(description);
    this.timing = timing;
  }

  public String getTiming() {
    return this.timing;
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + " (at:" + timing + ")";
  }
}