package task;

/**
 * A user's task.
 * Encapsulate details of the user's task.
 */
public abstract class Task {
    protected String description;
    boolean isDone;

    /**
     * Constructor for a task.
     * @param description the task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Used to store task into user's hard disk storage.
     * @return the format to store in DataBase.
     */
    public abstract String toDataBase();

    private String getStatusIcon() {
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

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}