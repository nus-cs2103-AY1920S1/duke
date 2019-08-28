package duke.task;

/**
 * Represents a task that can be marked as complete.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the {@link Task} as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true if the {@link Task} is complete, otherwise returns false.
     *
     * @return true if the {@link Task} is complete, otherwise returns false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a tick or X symbol.
     *
     * @return a tick or X symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a stringified version of the {@link Task} that is meant to be used in the {@link duke.storage.Storage}.
     *
     * @return a stringified version of the {@link Task} that is meant to be used in the {@link duke.storage.Storage}.
     */
    public String stringify() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
