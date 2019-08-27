/**
 * Represents a task that can be done or not done.
 * Provides methods for the user to mark the task as done, and to represent the task as a string suitable for
 * data storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for task. Assumes the task is not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor method for task. Allows the user to indicate whether the task is done.
     *
     * @param description Description of the task.
     * @param isDone Boolean for whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a tick icon if the task is done, and a cross if it is not.
     *
     * @return A tick or cross icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the status of the task to "done".
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Represents the done status and description of the task as a string.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Represents the task as a string in a format suitable for data storage.
     *
     * @return Data storage-friendly string representation of the task.
     */
    public String toStorageString() {
        String isDoneString = isDone ? "1" : "0";
        return isDoneString + "`" + description;
    }
}
