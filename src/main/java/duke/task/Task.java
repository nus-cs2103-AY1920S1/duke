package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of this task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of this task to be completed.
     *
     * @throws DukeException if the task has already been marked as done before
     */
    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This task was marked as done before.");
        }
        this.isDone = true;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected int getStatusCode() {
        return isDone ? 1 : 0;
    }
}