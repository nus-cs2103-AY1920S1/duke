package duke.task;

/**
 * Contains information about a task in the Duke Program.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, otherwise return false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon.
     *
     * @return the tick icon if task is done, otherwise returns a cross symbol.
     */
    public String getStatusIcon() {
        return (getIsDone() ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task to a suitable string format for storage.
     *
     * @return The task in a suitable string format for storage.
     */
    public abstract String toStorageFormat();

    /**
     * Gets the details of the task as a string.
     *
     * @return Details of the task as a string.
     */
    public String getDetails() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
