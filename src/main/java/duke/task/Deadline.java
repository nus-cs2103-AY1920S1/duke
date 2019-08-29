package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class representing an event, inherits from Task.
 */
public class Deadline extends Task {

    protected String by;
    protected Date date;

    /**
     * Constructor for deadline, to be called for instantiating this object.
     *
     * @param description The description of the task.
     * @param by The time at which the deadline is due.
     * @throws ParseException If input date format is invalid
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        this.date = formatter.parse(by);
    }

    /**
     * Another constructor for deadline, to be called when storage loads from data stored locally.
     *
     * @param description The description of the deadline.
     * @param isDone Define whether a deadline is done.
     * @param by The time at which the deadline is due.
     * @throws ParseException If input date format is invalid
     */
    public Deadline(String description, boolean isDone, String by) throws ParseException {
        super(description, isDone);
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        this.date = formatter.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string which is formatted to be stored in local storage.
     *
     * @return Returns a string which is formatted to be stored in local storage.
     */
    @Override
    public String getFileStringFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
