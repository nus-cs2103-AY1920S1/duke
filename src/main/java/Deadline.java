import java.util.Date;

/**
 * Represents a Deadline kind of task.
 * In addition to the information in the base Task object, also contains a Date object representing the task deadline.
 */
public class Deadline extends Task {
    protected Date by;

    /**
     * Constructor for Deadline. Assumes it is undone.
     *
     * @param description Description of the Deadline.
     * @param by Date of the task deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline. Allows user to indicate whether it is done.
     *
     * @param description Description of Deadline.
     * @param isDone Boolean representing whether the Deadline is done.
     * @param by Date of the task deadline.
     */
    public Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Represents the done status, description, and deadline of the task as a string,
     * with a flag indicating it is a Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }

    /**
     * Represents the Deadline as a string in a format suitable for data storage.
     *
     * @return Data storage-friendly string representation of the Deadline.
     */
    @Override
    public String toStorageString() {
        return "D`" + super.toStorageString() + '`' + by.toString();
    }
}
