package task;

/**
 * Represents a task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @param description of the task
     * @param isDone true if task is completed. Else false
     */
    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public String getDoneIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getDoneIcon() + "] " + description;
    }
}