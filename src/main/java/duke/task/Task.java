package duke.task;

import duke.exception.DukeException;

import static duke.ui.Ui.INDENT;

public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract Task copy();

    /**
     * Returns the description of this task.
     *
     * @return The description of this task
     */
    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
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

    /**
     * Converts an event into encoded form.
     *
     * @return the encoded form of this task
     */
    public abstract String encode();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Adds indentation in from of the string representation of this task
     * and returns the indented format of this task.
     *
     * @return the indented format of this task
     */
    public String getIndentedFormat() {
        return INDENT + this.toString();
    }

    private String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    protected int getStatusCode() {
        return isDone ? 1 : 0;
    }
}