package duke.task;

/**
 * Deadline tasks which extends from the Task class. Keeps track of tasks
 * that has to be completed within a specific time, indicated with "/by".
 */
public class Deadline extends Task {
    /**
     * This field stores the timing where the task has to be completed by.
     */
    private String by;

    /**
     * Constructor for the deadline class. Takes note of the description
     * of the task and also the timing that it has to be completed by.
     *
     * @param description Gives the main outline of the task.
     * @param by Gives the timing for the task to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Getter method to give the deadline of the specific task.
     *
     * @return the deadline of the specific task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Format the deadline task into a string.
     *
     * @return the deadline task formatted into a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
