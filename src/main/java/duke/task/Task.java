package duke.task;

public class Task {
    String description;
    boolean isDone;

    /**
     * Constructor to create a Task object.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the tick / cross icon for the status.
     *
     * @return The current status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the current completion status of the task.
     *
     * @return True if done, false if the task is yet to be completed.
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The proper representation of the task, with Task type, icon and description.
     *
     * @return The output representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}