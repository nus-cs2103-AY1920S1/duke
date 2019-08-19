/**
 * Task class. Holds the string of the task, and whether it is done.
 */
public class Task {
    /** Whether task is done. */
    private boolean isDone;
    /** String of the task. */
    private String description;

    /**
     * Constructor.
     * @param description String of the task.
     */
    Task(String description) {
        this.description = description;
    }

    /**
     * Changes isDone to true, setting the task to be done.
     */
    void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the string symbol of whether the task is done.
     * @return String symbol of whether the task is done.
     */
    private String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
