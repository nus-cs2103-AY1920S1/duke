public class Task {

    private boolean isDone;
    protected String description;
    protected String time;
    protected String label;

    /**
     * Creates a new instance of Task.
     *
     * @param s description of the task.
     */
    public Task(String s) {
        this.isDone = false;
        this.description = s;
    }

    /**
     * Creates a new instance of Task.
     *
     * @param s description of the task.
     * @param t time of the task.
     */
    public Task(String s, String t) {
        this.isDone = false;
        this.description = s;
        this.time = t;
    }

    /**
     * Returns a label which is a representation of the task type.
     *
     * @return the label of the Task.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the time of the task.
     *
     * @return time of the task.
     */
    public String getTime() {
        if (this.time != null) {
            return time;
        } else {
            return "";
        }
    }

    /**
     * Returns the status of the task.
     *
     * @return status of the task.
     */
    public int getStatus() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates status of task by marking this task as done.
     * Boolean value <code>isDone</code> is updated to <code>true</code>.
     */
    public void mark() {
        // Update status of task
        if (!isDone) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representing tick or X symbols.
     *
     * @return string representation of tick or X symbols.
     */
    protected String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "[\u2713] " : "[\u2718] ");
    }

}
