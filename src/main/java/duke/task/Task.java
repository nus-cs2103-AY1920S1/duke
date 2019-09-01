package duke.task;

import duke.DukeException;

import java.io.Serializable;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String d) {
        description = d;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2717"; // ✓ or ✗
    }

    public String getDescription() {
        return description;
    }

    public void markDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done!");
        }
        isDone = true;
    }

    public abstract String toString();
}
