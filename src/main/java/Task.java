/**
 * Task Class.
 *
 * Represents a single task.
 *
 * @author Marcus Ong
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "x"); //return + or x symbols
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}