public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return A tick symbol when this task is done or a X symbol when this task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter for this task's description.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for whether this task is done.
     *
     * @return Whether this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
