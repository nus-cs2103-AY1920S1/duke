package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A child class of Task class that represents an event Task
 * which has description and time.
 */
public class Event extends Task {

    protected String time;
    protected Date date;

    /**
     * Constructs an Event object with the specified description and time.
     * @param description Description that describe the event task.
     * @param time The time at which the task is done.
     * @throws ParseException if time does not follow the date and time format.
     */
    public Event(String description, String time) throws ParseException {
        super(description);
        this.time = time;
        super.typeOfTask = "E";
        SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy hhmm");
        this.date = formatter.parse(time);
    }

    /**
     * @return the time at which the task is done.
     */
    public String getTime() {
        return this.time;
    }

    public String toString() {
        return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + time + ")";
    }
}
