public abstract class Task {
    protected boolean isDone = false;
    protected String description;
    
    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets task status.
     * @return Task status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns task description.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * @return Unicode character representing status of task.
     */
    public String getStatusIcon() {
        return this.isDone ? "✓" : "✘";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
