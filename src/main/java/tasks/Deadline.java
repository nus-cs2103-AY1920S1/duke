package tasks;

import java.util.Date;

/**
 * Represents a deadline.
 *
 * @author Michelle Yong
 */
public class Deadline extends Task {
    /**
     * Creates a deadline with specified description and date.
     *
     * @param description The description of the deadline.
     * @param by The date that the deadline is due.
     */
    public Deadline(String description, Date by) {
        super(description);
        super.date = by;
        super.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + super.date + ")";
    }
}