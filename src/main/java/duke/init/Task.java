package duke.init;

/**
 * Implements a task.
 * @author lyskevin
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * @param description The specified description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns true if this task is done.
     * @return true if this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets this task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of this task.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String statusIcon = isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", statusIcon, description);
    }

}
