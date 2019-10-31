package duke.task;

/**
 * Deadline class to create the deadline task
 * input by user.
 *
 * @author TeoShyanJie
 *
 */
public class Deadline extends Task {
    /** Deadline of the task. */
    protected String by;

    /**
     * Constructor of the Deadline class.
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get time of deadline.
     * @return String of deadline time.
     */
    public String getBy() {
        return by;
    }

    /**
     * Return the task as string.
     * @return String of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}