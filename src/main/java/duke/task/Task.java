package duke.task;

/**
 * The base class all Task classes are to inherit from.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon corresponding to whether the task is complete or not.
     *
     * @return "O" if the task is complete, "X" otherwise.
     */
    private String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    /**
     * Marks a task as complete.
     *
     * @return whether the task is complete or not.
     */
    protected boolean markAsDone() {
        this.isDone = true;
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + ']' + ' ' + description;
    }
}
