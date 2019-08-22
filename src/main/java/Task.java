/**
 * Encapsulates a task handled by Duke.
 *
 * A task has a description and a status, for each of which getter methods have
 * been provided. The task status can also be updated when a task is done.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */

public class Task {
    /** The description of the task */
    private String description;

    /** The status of the task - done or not done */
    private boolean isDone;

    /**
     * Creates a task with a description and a status.
     *
     * @param description string representing the description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the unicode icon indicating task status.
     *
     * @return tick mark if task is done or cross mark if task is not done.
     */
    private String getTaskStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
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
    public void setTaskAsDone(boolean hasBeenDone) {
        this.isDone = hasBeenDone;
    }

    /**
     * Return a string representation of this task.
     *
     * @return string representing this task.
     */
    public String toString() {
        return "[" + this.getTaskStatusIcon() + "] " + this.description;
    }
}
