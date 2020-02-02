package duke.task;

import duke.exception.IllegalDescriptionException;

import java.time.LocalDateTime;

/** A class that represents a task. */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task Object.
     * @param description task description
     */
    public Task(String description) throws IllegalDescriptionException {
        if (description.isEmpty()) {
            throw new IllegalDescriptionException("The description of a "
                    + getClass().getSimpleName().toLowerCase() + " cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns task type.
     * @return task type.
     */
    public abstract TaskType getTaskType();

    /**
     * Returns the date and time associated with the task.
     * @return the date and time associated with the task.
     */
    public abstract LocalDateTime getDateTime();

    /**
     * Returns the status of the task.
     * @return true if the task is done, false if not done.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns an icon showing the status of the task.
     * @return tick icon if the task is done otherwise X icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set the status of the task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task to be stored in file.
     * @return a string representation of the task to be stored in file, consisting of the task type,
     *         status, description.
     */
    public String toStringForFile() {
        return String.format("%s | %d | %s", getClass().getSimpleName().substring(0,1), isDone ? 1 : 0, description);
    }

    /**
     * Returns a String representation of the task.
     * @return a String representation of the task, consisting of status icon
     *         and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
