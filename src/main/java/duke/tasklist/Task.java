package duke.tasklist;

/**
 * Abstract class representing a task which can be added to a task list
 */
public abstract class Task {
    boolean taskCompletionStatus;
    private String taskDescription;

    /**
     * Default constructor for classes which extend from Task
     * @param description The description of the task
     */
    public Task(String description) {
        taskCompletionStatus = false;
        taskDescription = description;
    }

    /**
     * Sets the completion status of the task to completed
     *
     * @return This task which has been marked as completed
     */
    public Task complete() {
        taskCompletionStatus = true;
        return this;
    }

    /**
     * Returns if the task has been completed or not
     *
     * @return True if the task is complete, false otherwise
     */
    public boolean isComplete() {
        return taskCompletionStatus;
    }

    /**
     * Returns the description of the task
     *
     * @return The description of the task
     */
    public String getDescription() {
        return new String(taskDescription);
    }

    /**
     * Returns the string representation of the task
     *
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return new StringBuilder("[").append((taskCompletionStatus ? "✓" : "✗"))
                .append("] ")
                .append(taskDescription)
                .toString();
    }
}
