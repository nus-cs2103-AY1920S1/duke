package duke.task;

/**
 * Represents a task to be tracked by Duke.
 */
public abstract class Task {
    /** Description of task. */
    String description;
    /** Whether the task is done or not. */
    boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        assert !description.equals("") : "Description of a Task should not be empty.";

        this.description = description;
        this.isDone = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return this.description.equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    /**
     * Returns the status icon regarding whether the task is done or not.
     *
     * @return Tick symbol if task is done, otherwise return cross symbol.
     */
    private String getStatusIcon() {
        return isDone ? "O" : "X";
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
