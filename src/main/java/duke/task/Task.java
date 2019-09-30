package duke.task;

/**
 * A representation a task.
 * The base class for all the other task classes.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Public constructor for Task class.
     * Creates an incomplete (status-wise) task by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     * @return The description of the task in String format.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the isDone variable to either a "tick" or a "cross" symbol.
     *
     * @return The status of the task (completed or not) in symbol form ("tick" or "cross").
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts information about the task to String format (for addition to the data file).
     *
     * @return Task information in String format.
     */
    public String printToTxtFile() {
        return String.format("T | %d | %s | %s", isDone ? 1 : 0, description);
    }

    /**
     * Converts information about the task to String format (for printing to the UI).
     *
     * @return Task information in String format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
