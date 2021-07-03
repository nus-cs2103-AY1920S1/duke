import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 42L;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param description is the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets description of Task.
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}