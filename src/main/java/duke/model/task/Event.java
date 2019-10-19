package duke.model.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class representing an event, inherits from Task.
 */
public class Event extends Task {

    private String at;
    private Date date;

    /**
     * Constructor for event, to be called for instantiating this object.
     *
     * @param description The description of the event.
     * @param at The time at which the event occurs.
     * @throws ParseException If input date format is invalid
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        try {
            this.date = formatter.parse(at);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage() + "\nPlease use the format: dd/MM/yyyy hhmm");
        }
    }

    /**
     * Another constructor for event, to be called when storage loads from data stored locally.
     *
     * @param description The description of the event.
     * @param isDone Define whether an event is done.
     * @param at The time at which the event occurs.
     * @throws DukeException If input date format is invalid
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        this.at = at;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        try {
            this.date = formatter.parse(at);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage() + "\nPlease use the format: dd/MM/yyyy hhmm");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a string which is formatted to be stored in local storage.
     *
     * @return Returns a string which is formatted to be stored in local storage.
     */
    @Override
    public String getFileStringFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.at;
    }

    public String getAt() {
        return at;
    }

    /**
     * Sets 'at' and 'date' field according to the argument of the method.
     *
     * @param at The new 'at' field
     * @throws DukeException If input date format is invalid
     */
    public void setAt(String at) throws DukeException {
        this.at = at;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        try {
            this.date = formatter.parse(at);
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
