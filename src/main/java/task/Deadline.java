package task;

import java.util.Date;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    protected Date by;

    /**
     * Initializes Deadline with description and due date.
     *
     * @param description of deadline
     * @param by is the due date of deadline
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes Deadline with description, due date and isDone status.
     *
     * @param description of deadline
     * @param by is the due date of deadline
     * @param isDone true if deadline is completed. Else false
     */
    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Date getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}