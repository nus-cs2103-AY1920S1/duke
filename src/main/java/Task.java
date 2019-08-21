public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new undone Task with the given description.
     * @param description   Description of the Task. Description length should
     *                      be at most 50 characters (for now).
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or X symbol according to the isDone status of the
     * current task.
     * @return  A tick symbol if the Task is done, and an X symbol otherwise.
     */
    private String getStatusIcon() {
        return isDone ? "+" : " ";
        // return isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * Indicates that the current Task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Indicates that the current Task has not been completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the Task along with an indication of its
     * isDone status.
     * @return  String containing the status and description of the current
     *          Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
