package task;

import duke.DukeException;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    // Invalid task throws DukeException
    public Task(String action, int size) throws DukeException {
        if (size == 1) {
            throw new DukeException("    " + "\u2639" + " OOPS!!! The description of a " + action + " cannot be empty.");
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toSave() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

}
