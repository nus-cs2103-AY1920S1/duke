import java.util.Date;

/** Class to represent a task. */
abstract class Task {
    protected String name;
    protected boolean done;
    protected TaskType type;

    /**
     * Constructor for the object.
     * @param name Description of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Mark a task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Get the task description.
     * @return String representing task description.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Check if task is marked done.
     * @return Boolean representing task done status.
     */
    public boolean isTaskDone() {
        return this.done;
    }

    /**
     * Abstract method to represent the task type.
     * @return TaskType of the task.
     */
    public abstract TaskType getType();

    /**
     * Abstract method representing the task date.
     * @return Date of task.
     */
    public abstract Date getDate();
}