package duke;

/**
 * Represents a Task object. A <code>Task</code> object corresponds to
 * an agenda to be done.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick symbol if task is done.
     * Otherwise, returns X.
     *
     * @return String tick or X symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets done status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

}
