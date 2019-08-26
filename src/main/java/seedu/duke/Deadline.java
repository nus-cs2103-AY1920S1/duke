package seedu.duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task which extends <code>Task</code>. A <code>Deadline</code>
 * object can take in a description, date and time to complete the task by.
 */
public class Deadline extends Task {
    protected DateFormat outDateFormat = new SimpleDateFormat( "dd/MM/yyyy");
    protected DateFormat outTimeFormat = new SimpleDateFormat("H.mm aa");
    protected SimpleDateFormat fileDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected SimpleDateFormat fileTimeFormat = new SimpleDateFormat("HHmm");
    protected Date date, time;

    /**
     * Constructor of Deadline.
     *
     * @param description String description of the task.
     * @param date Date of the deadline as a <code>Date</code> object.
     * @param time Time of the deadline as a <code>Date</code> object.
     */
    public Deadline(String description, Date date, Date time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * String representation of the deadline task.
     *
     * @return String with the type of [task] [tick or cross] description (by:date time).
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outDateFormat.format(date) + " " + outTimeFormat.format(time) + ")";
    }

    /**
     * String representation of the deadline task with its description, date and time
     * to write into the data file.
     *
     * @return String with the type of task | boolean of whether done | description | date time.
     */
    @Override
    public String toWriteIntoFile() {
        return "D" + super.toWriteIntoFile() + " | " + fileDateFormat.format(date) + " " + fileTimeFormat.format(time);
    }
}
