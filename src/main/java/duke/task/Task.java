package duke.task;

/**
 * Represents a task to be tracked by Duke.
 */
public abstract class Task {
    /** Description of task. */
    protected String description;
    /** Whether the task is done or not. */
    protected boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon regarding whether the task is done or not.
     *
     * @return Tick symbol if task is done, otherwise return cross symbol.
     */
    public String getStatusIcon() {
        return isDone ? "+" : " ";
    }

    /**
     * Returns the print format of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the save format of the task.
     *
     * @return Formatted string to be saved in storage file.
     */
    public abstract String getSaveString();

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }
}
