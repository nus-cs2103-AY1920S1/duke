package tasks;

import exceptions.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or a cross as a string depending on whether task is completed.
     * @return string or cross as a string
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a boolean indicating whether task is completed.
     * @return boolean if task is completed or not
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns description of task as a String.
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the boolean isDone as true.
     */
    public boolean markAsDone() throws DukeException {
        if (!this.isDone) {
            this.isDone = true;
            return this.isDone;
        } else {
            throw new DukeException("Task has already been marked as done");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    //...
}