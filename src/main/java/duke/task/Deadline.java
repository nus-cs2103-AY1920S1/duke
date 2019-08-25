package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

public class Deadline extends Task {

    protected Date date;
    protected Time time;

    public Deadline(String description, Date date, Time time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + (time.toString().equals("") ? "" : ", " + time.toString()) + ")";
    }

    public String getUnprocessedDate() {
        return date.getUnprocessedDate();
    }

    public String getUnprocessedTime() {
        return time.getUnprocessedTime();
    }

    public String getType() {
        return "D";
    }
}
