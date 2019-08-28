package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected int getStatusCode() {
        return isDone ? 1 : 0;
    }

    /**
     * Sets the status of the task to be completed.
     *
     * @throws DukeException if the task has already been marked as done before
     */
    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This task was marked as done before.");
        }
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public abstract String serialize();
}