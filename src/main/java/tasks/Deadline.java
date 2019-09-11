package tasks;

import java.util.Date;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    /**
     * Creates a deadline object.
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
        return "[D]" + super.toString() + " (by: " + super.date + ")";
    }
}