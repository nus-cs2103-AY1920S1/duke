package duke.task;

/**
 * Represents a Task that has to be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or cross to represent the completion status of a task.
     *
     * @return A tick if done, cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a boolean value representing the task's completion status
     *
     * @return True if done, false if not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task to the input boolean.
     *
     * @param done The boolean value to set the completion status to.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        setDone(true);
    }
    /**
     * Returns an ASCII description of the task.
     *
     * @return ASCII representation of the task.
     */
    public String getAscii() {
        String doneOrNot = isDone() ? "1" : "0";
        return doneOrNot + " | " + getDescription();
    }

    /**
     * Returns a Unicode description of the task.
     *
     * @return Unicode representation of the task.
     */
    @Override
    public String toString() {
        String value = "[" + getStatusIcon() + "]" + " " + getDescription();
        return value;
    }
}