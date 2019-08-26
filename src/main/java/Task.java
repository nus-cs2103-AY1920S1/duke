class Task {
    private final String description;
    private boolean isDone;

    /**
     * Initialises a Task.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of a task by checking if it is done or not.
     *
     * @return The string representation of the icon.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;

    }

    /**
     * The string representation of a task for printing.
     *
     * @return The task string for printing.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * The string representation of a task for saving to file.
     *
     * @return The task string for storage.
     */
    public String toStore() {
        return isDone ? "|1|" + this.description : "|0|" + this.description;
    }
}