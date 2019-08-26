package task;

/**
 * A user's task.
 * Encapsulate details of the user's task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task
     * @param description the task's description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Used to store task into user's hard disk storage.
     * Will be further overrided in child's class.
     * @return nothing, or an empty string
     */
    public String toDataBase() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark this task's status as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * @return status and description of this task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}