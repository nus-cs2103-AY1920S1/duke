package duke.task;

import duke.DukeException;

import java.io.Serializable;

/**
 * This class provides the skeleton for a Duke Task.
 * To implement this class, the programmer only needs to extend this class and provide an implementation for
 * the {@link #toString()} method.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String d) {
        description = d;
        isDone = false;
    }

    /**
     * Returns a short String representing the status of the Task (e.g. whether it is done or undone).
     * It should be as short as possible, ideally a single code point, to facilitate display in a list.
     *
     * @return  String representing the status of the Task
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2717"; // ✓ or ✗
    }

    /**
     * Returns the description of the Task.
     *
     * @return  the Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks this Task as done.
     *
     * @throws DukeException  if the task is already done
     */
    public void markDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done!");
        }
        isDone = true;
    }

    public abstract String toString();
}
