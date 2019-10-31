package duke.task;

import duke.util.DukeException;

/**
 * An abstract Task that can be a to-do, deadline, or event task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a new Task with a given description and false done status.
     * @param description The task's description as a String.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task's done status to true.
     * @throws DukeException If task is already marked done.
     */
    public void markDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task is already marked done.");
        }
        this.isDone = true;
    }

    /**
     * Returns the task's done status and description as a String.
     * @return A String in the format: [+] task_description
     */
    @Override
    public String toString() {
        String doneIcon = isDone ? "+" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}
