/**
 * The Deadline class represents a type of task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a deadline task.
     *
     * @param description A string representing the description of the task
     * @param by A string representing the deadline of that task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the task description, deadline and status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}