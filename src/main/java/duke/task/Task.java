package duke.task;

/**
 * The task the user does.
 */
public abstract class Task {
    protected String description; //The description of the task.
    protected boolean isDone; //Whether the task is done.
    protected Type type; //The type of the task.

    /**
     * Initiates the object.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Gets the status icon.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Turns task to string.
     *
     * @return Task in string.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }

    /**
     * Turns task to file format.
     *
     * @return Task in file format.
     */
    public String toFile() {
        String doneState = isDone ? "1" : "0";
        return String.format("%s | %s | %s", type, doneState, description);
    }
}

