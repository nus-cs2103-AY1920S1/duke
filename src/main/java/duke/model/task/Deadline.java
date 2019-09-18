package duke.model.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class representing an event, inherits from Task.
 */
public class Deadline extends Task {

    private String by;
    private Date date;

    /**
     * Constructor for deadline, to be called for instantiating this object.
     *
     * @param description The description of the task.
     * @param by The time at which the deadline is due.
     * @throws ParseException If input date format is invalid
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        try {
            this.date = formatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage() + "\nPlease use the format: dd/MM/yyyy hhmm");
        }
    }

    /**
     * Another constructor for deadline, to be called when storage loads from data stored locally.
     *
     * @param description The description of the deadline.
     * @param isDone Define whether a deadline is done.
     * @param by The time at which the deadline is due.
     * @throws DukeException If input date format is invalid
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        try {
            this.date = formatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage() + "\nPlease use the format: dd/MM/yyyy hhmm");
        }
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

    /**
     * Sets 'by' and 'date' field according to the argument of the method.
     *
     * @param by The new 'by' field
     * @throws DukeException If input date format is invalid
     */
    public void setBy(String by) throws DukeException {
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        try {
            this.date = formatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage() + "\nPlease use the format: dd/MM/yyyy hhmm");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
