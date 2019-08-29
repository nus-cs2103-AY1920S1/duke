package slave.task;

/**
 * Abstract class which represents a task
 */
public abstract class Task {

    private String description;
    private boolean isDone;
    private int id;
    TaskType type;

    /**
     * Constructor for task.
     *
     * @param description Task description.
     * @param id Task id.
     */
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    /**
     * Decrements id of task.
     */
    public void decrementId() {
        this.id--;
    }

    /**
     * Gets task id.
     *
     * @return Task id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Ges task type.
     *
     * @return Task type.
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Gets task description.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets task date.
     * @return Task date.
     */
    public String getDate() {
        return "";
    }

    /**
     * Gets appropriate status icon to represent whether a task has been done.
     * @return Corresponding status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "Done" : "X");
    }

    /**
     * Gets a boolean value on whether task is done.
     * @return Boolean value of whether a task is done.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Sets task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Converts task to an appropriate String representation with the status.
     *
     * @return Formatted string.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
