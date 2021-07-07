package tasks;

/**
 * Encapsulates a task handled by Duke.
 *
 * <p> A task has a description and a status, for each of which getter methods have
 * been provided. The task status can also be updated when a task is done. </p>
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */

public class Task {
    /** The description of the task. */
    private String description;

    /** The status of the task - done or not done. */
    private boolean isDone;

    /**
     * Creates a task with a description and a status.
     *
     * @param description string representing the description of this task
     * @param isDone flag indicating whether task has been done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the icon indicating task status.
     *
     * @return "Y" if task is done or "N" if task is not done.
     */
    private String getTaskStatusIcon() {
        return (this.isDone ? "Y" : "N"); // return Y or N
        // return (this.isDone ? "\u2713" : "\u2718"); // return tick or X
    }

    /**
     * Get current status of this task.
     *
     * @return true if current task is done, false if current task is not done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Changes the status of the task to 'done'.
     *
     * @param hasBeenDone boolean to indicate that the task has been completed.
     */
    public boolean setTaskAsDone(boolean hasBeenDone) {
        this.isDone = hasBeenDone;
        return this.isDone;
    }

    /**
     * Return a string representation of this task.
     *
     * @return string representing this task.
     */
    public String toString() {
        return this.getTaskStatusIcon() + "-" + this.description;
    }

    /**
     * Return task description of this task.
     *
     * @return string representing task description.
     */
    public String getTaskDescription() {
        return this.description;
    }
}
