package duke.task;

/**
 * Abstraction of a task object.
 */
public abstract class Task {
    /** Whether the task is completed. */
    private boolean isDone;
    /** The input timing string as entered by the user. */
    private String timing;

    /** The description of the task. */
    String description;
    /** The TaskType enum value of the task. */
    TaskType taskType;

    /**
     * Generic constructor for tasks with only a description.
     *
     * @param description The description input string of the task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
        this.timing = null;
    }

    /**
     * Generic constructor for tasks with a timing and description.
     *
     * @param description The description input string of the task.
     * @param timing The timing input string of the task.
     */
    Task(String description, String timing) {
        this(description);
        this.timing = timing;
    }

    /**
     * Retrieves whether the task is completed.
     *
     * @return Bool of task completion status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieves the appropriate unicode character for the done status.
     *
     * @return The respective unicode character.
     */
    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Retrieves the TaskType enum value of the task.
     *
     * @return The TaskType of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the input timing string entered by the user.
     *
     * @return The input timing string.
     */
    public String getTiming() {
        return this.timing;
    }

    /**
     * Sets the done status of the task.
     *
     * @param done Boolean of the done status.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Abstraction of a method retrieving task type specific status strings.
     *
     * @return The task type specific status string.
     */
    abstract public String getStatusText();
}
