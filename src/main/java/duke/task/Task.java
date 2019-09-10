package duke.task;

/**
 * Handles different types of Tasks, including events, deadlines, and todos.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon based on boolean value of isDone
     *
     * @return Unicode character check mark if isDone is true, else returns unicode character heavy ballot.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets the description of this Task's instance.
     *
     * @return String description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task's instance as done by toggling the isDone attribute.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Saves a dummy string (is overridden by subclasses of Task).
     *
     * @return Empty string.
     */
    public String toSave() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
