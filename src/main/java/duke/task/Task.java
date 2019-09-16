package duke.task;

/**
 * Abstract class representing a Task to be added.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs an instance of Task.
     *
     * @param description the description of the task to be added.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Informs the completion status of a particular Task.
     *
     * @return completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "☓");
    }

    /**
     * Marks a Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Informs the description of a particular Task.
     *
     * @return the description of the Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Provides the string representation of an instance of Task.
     *
     * @return the task's string representation.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
