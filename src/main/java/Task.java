/**
 * Represents a Task to be completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with given description.
     * @param description The description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Changes the Task to done.
     */
    public void markAsDone() {
        isDone = true;
    }
}
