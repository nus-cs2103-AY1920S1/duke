/**
 * The Task class represents the things to do.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description A string representation of the task's description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets a string representation of whether the task is done.
     *
     * @return A string that is either a tick or X symbol depending on whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }
    /**
     * Gets the task description and an icon depending on whether it is done.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }
}