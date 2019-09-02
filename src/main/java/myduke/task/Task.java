package myduke.task;

/**
 * An abstract class to represent a Task.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return A Character representing the status of the current task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the current task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the database string format.
     *
     * @return A database string format
     */
    public abstract String getDataBaseFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}