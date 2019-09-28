package duke;

import java.io.Serializable;

/**
 * This class represents a basic Task.
 */
public class Task implements Serializable {
    protected String description;
    private boolean isDone;

    /**
     * Returns an instance of a Task.
     * @param description indicates the description of the task.
     */
    public Task(String description) {
        assert !description.equals("") : "Task description empty!";
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns a tick or cross based on the status of the task.
     * @return a character \u2713 (tick) or \u2718 (cross)
     *     representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets a task status to Done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of a Task.
     * @return a String representation of a Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
