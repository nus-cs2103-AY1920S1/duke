package task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes Task with description.
     *
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes Task with description and isDone status.
     *
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getDoneIcon() + "] " + description;
    }

    /**
     * Converts task object to format use in file save.
     * @return String that is in file save format
     */
    public abstract String toSaveFormat();
}