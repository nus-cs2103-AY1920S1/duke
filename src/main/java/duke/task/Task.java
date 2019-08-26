package duke.task;

/**
 * Represents a task.
 * Abstract parent class of all specific types of <code>Task</code>.
 * A <code>Task</code> object corresponds to a task that has to be done.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor of <code>Task</code>.
     * @param description Provided details of the <code>Task</code>.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the icon corresponding to the status of the <code>Task</code>.
     * @return Tick icon if task is completed and cross icon otherwise.
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
     * Returns the provided details.
     * @return Provided details.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task has been completed.
     * @return True if the task has been completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the first letter of the task type.
     */
    public abstract String getType();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
