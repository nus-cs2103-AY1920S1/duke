package q.task;

import q.main.DateTimeParser;

import java.util.Date;

/**
 * An Deadline Task. Has a description and a date and time of the deadline.
 */
public class Deadline extends Task {

    private Date by;

    /**
     * Constructs an undone deadline with a description and date.
     *
     * @param description The description of the deadline.
     * @param by          The date and time of the deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline with a description, date, and a doneness.
     *
     * @param description The description of the deadline
     * @param isDone      The doneness of the deadline.
     * @param by          The date and time of the deadline.
     */
    public Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Converts the task to a string, including the doneness of the deadline, its description, and the date and time.
     *
     * @return A string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.format(by) + ")";
    }

    /**
     * Converts the deadline to an encoded string for saving.
     *
     * @return A string encoding the deadline for saving.
     */
    public String toSave() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + DateTimeParser.format(by);
    }
}