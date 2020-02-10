package czkay.duke.model.task;

import java.io.Serializable;

/**
 * Represents the task given by the user.
 */
public abstract class Task implements Serializable {
    String taskDescription;
    boolean isDone;

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    /**
     * Marks this task as complete.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Check if the task is a timed task.
     *
     * @return A boolean value for whether the task is a timed task.
     */
    public boolean isTimed() {
        return false;
    }

    /**
     * Check if the task is complete.
     * @return A boolean value for whether the task is complete.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public abstract String toString();

}
