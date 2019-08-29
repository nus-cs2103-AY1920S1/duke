package tasks;

import java.io.Serializable;


/**
 * A tasks.Task class representing a task with a isDone component.
 */
public abstract class Task implements Serializable {
    String description;
    private boolean isDone;
    private static final long serialVersionUID = 42L;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}





