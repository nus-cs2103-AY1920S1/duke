package duke.task;

import java.util.Calendar;

/**
 * Represents an Event with a name and datetime
 */
public class Event extends Task {
  private Calendar datetime = Calendar.getInstance();
  private String datetimeString;

  public Event(String name, String datetime) {
    this(name, datetime, false);
  }

  Event(String name, String datetime, boolean done) {
    super(name, done);
    this.datetimeString = datetime;
    String[] dateandtime = datetime.split(" ");
    String[] date = dateandtime[0].split("/");
    this.datetime.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
        Integer.parseInt(dateandtime[1].substring(0, 2)), Integer.parseInt(dateandtime[1].substring(2, 4)));
  }

  /**
   * Returns the datetime of the Deadline instance in string
   * @return String: datetime
   */
  public String getDatetimeString() {
    return datetimeString;
  }

  public Calendar getDatetime() {
    return datetime;
  }

  /**
   * Returns a string formatted for storage in the storage file.
   * @return String
   */
  @Override
  public String storageString() {
    return "E," + (super.getDone() ? "1," : "0,") + super.getName() + "," + this.datetimeString;
  }

  /**
   * Returns a string for normal printing to represent state and details of the Task.
   * @return String
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + datetimeString + ")";
  }

  /**
   * Checks for logical equality of this instance to another object
   * @param obj Another object in question.
   * @return true if logically equivalent, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (obj instanceof Event) {
      Event other = (Event) obj;
      return this.getDone() == other.getDone() && this.getName().equals(other.getName())
              && this.getDatetimeString().equals(other.getDatetimeString());
    }
    return false;
  }
}
