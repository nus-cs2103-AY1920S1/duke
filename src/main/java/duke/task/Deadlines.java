package duke.task;

import java.util.Calendar;

public class Deadlines extends Task {
  private Calendar datetime = Calendar.getInstance();
  private String datetimeString;

  public Deadlines(String name, String datetime) {
    this(name, datetime, false);
  }

  Deadlines(String name, String datetime, boolean done) {
    super(name, done);
    this.datetimeString = datetime;
    String[] dateandtime = datetime.split(" ");
    String[] date = dateandtime[0].split("/");
    this.datetime.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
        Integer.parseInt(dateandtime[1].substring(0, 2)), Integer.parseInt(dateandtime[1].substring(2, 4)));
  }

  @Override
  public String storageString() {
    return "D," + (super.getDone() ? "1," : "0,") + super.getName() + "," + this.datetimeString;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + datetimeString + ")";
  }
}
