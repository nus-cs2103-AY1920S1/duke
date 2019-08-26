package seedu.duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the event task which extends <code>Task</code>. A <code>Event</code> object
 * can take in a description, date and time which it is at.
 */
public class Event extends Task {
    protected DateFormat outDateFormat = new SimpleDateFormat( "dd/MM/yyyy");
    protected DateFormat outTimeFormat = new SimpleDateFormat("H.mm aa");
    protected SimpleDateFormat fileDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected SimpleDateFormat fileTimeFormat = new SimpleDateFormat("HHmm");
    protected Date date, time;

    /**
     * Class constructor.
     *
     * @param description String description of the event task.
     * @param date Date of the event as a <code>Date</code> object.
     * @param time Time of the event as a <code>Date</code> object.
     */
    public Event(String description, Date date, Date time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * String representation of the event task with its description, date and time.
     *
     * @return String with the type of [task] [tick or cross] description (at:date time).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + outDateFormat.format(date) + " " + outTimeFormat.format(time) + ")";
    }

    /**
     * String representation of the event task with its description, date and time
     * to write into the data file.
     *
     * @return String with the type of task | boolean of whether done | description | date time.
     */
    @Override
    public String toWriteIntoFile() {
        return "E" + super.toWriteIntoFile() + " | " + fileDateFormat.format(date) + " " + fileTimeFormat.format(time);
    }
}
