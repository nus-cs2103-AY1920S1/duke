/**
 * Inherits from the Task class and contains information about the Deadline tasks.
 *
 */
public class Deadline extends Task {
    private String byDate;

    /**
     * Creates a deadline task with description, deadline and status (done or not done) .
     *
     * @param description the description of the task.
     * @param by the deadline for the task.
     * @param b the status of the task.
     */
    public Deadline(String description, String by, boolean b) {
        super(description);
        this.byDate = by;
        //used while loading data from file to update the status of the task
        this.isDone = b;
    }

    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
    }

    /**
     *Method to display the information in the required form.
     *
     * @return the description of the task with a prefix D indicating the nature of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}
