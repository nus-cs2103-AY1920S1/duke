package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String description;
    private boolean isDone = false;

    /**
     * Creates a new task.
     *
     * @param description Task description.
     */
    Task(String description) {
        this.description = description;
    }

    /**
     * Getter for isDone.
     *
     * @return isDone.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Getter for description.
     *
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a cross or tick icon based on whether the task is done.
     *
     * @return Cross or tick icon.
     */
    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * Generates the task's string representation.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
