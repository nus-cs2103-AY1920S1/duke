/**
 * The Deadline class creates a Deadline task
 * that extends from the Task class.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for class Deadline.
     *
     * @param description The Deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return by Deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Overrides the toString method to print the deadline task.
     *
     * @return String Deadline task formatted to string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}