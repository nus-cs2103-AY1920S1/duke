package task;

/**
 * Task Class.
 *
 * <p>Represents a single task.
 *
 * @author Marcus Ong
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Task constructor.
     *
     * @param description Description of task.
     * @param type Type of task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Get a status symbol for the task.
     *
     * @return + for a done task or x for an undone task.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "x"); //return + or x symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}