/**
 * Task Class.
 *
 * Represents a single task.
 *
 * @author Marcus Ong
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
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