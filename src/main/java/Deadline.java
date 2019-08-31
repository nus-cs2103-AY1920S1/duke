/**
 * Inherits from the Task class and contains information about the Deadline tasks.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a deadline task with description, deadline and status (done or not done) .
     *
     * @param description the description of the task.
     * @param by the deadline for the task.
     * @param b the status of the task.
     */
    public Deadline(String description, String by, boolean b) {
        super(description);
        this.by = by;
        this.isDone = b;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *Method to display the information in the required form.
     *
     * @return the description of the task with a prefix D indicating the nature of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
