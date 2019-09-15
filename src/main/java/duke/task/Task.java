package duke.task;

/**
 * Task class as the parents of all task.
 *
 * @author TeoShyanJie
 *
 */
public class Task {
    /** Description of the task. */
    protected String description;

    /** The status of the task. */
    protected boolean isDone;

    protected String time;

    /**
     * Constructor of Task class.
     * @param description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = null;

    }

    /**
     * Get the status icon of the task.
     * @return status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    /**
     * Get the task as string.
     * @return task as string.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
