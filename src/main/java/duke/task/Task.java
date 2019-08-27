package duke.task;

/**
 * Represents an Task object. A <code>Task</code> object
 * specifies whether it has been completed and its item description.
 */
public class Task {

    private String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols as + and - respectively
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "| " + getStatusIcon() + " | " + description + " ";
    }
}
