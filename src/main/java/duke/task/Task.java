package duke.task;

/**
 * This is the task item to be added into the list of tasks. The <code>Task</code> abstract class provides an
 * abstraction over {@link Todo}, {@link Deadline}, {@link Event} items.
 */
public abstract class Task {

    /**
     * This is the description of the task.
     */
    protected String description;

    /**
     * This is used to indicate whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructs a new task with description that is not done.
     * @param description the description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new task with description and the option to mark it as done.
     * @param description the description of the task
     * @param isDone the done status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Encodes the task to be stored in the storage.
     * @return a string representation of the encoded task
     */
    public abstract String encode();

    /**
     * Gets the status icon of the done status of the task.
     * @return a status icon of the done status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task. The string representation consist of the status icon with the
     * task description.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
