package q.task;

/**
 * An abstract class that represents a task.
 */
public abstract class Task {
    String description;
    boolean isDone;

    /**
     * Constructs an undone Task with a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with a description and a note of if it is done.
     *
     * @param description Description of the task.
     * @param isDone      Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets a status icon representing the doneness of the task.
     *
     * @return A tick or cross symbol representing the doneness of the task.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return A boolean representing if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts the task to a string, including the doneness of the task and its description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to an encoded string for saving.
     *
     * @return A string encoding the task for saving.
     */
    public abstract String toSave();

    /**
     * Sets the task as done.
     */
    public void setDone() {
        isDone = true;
    }
}