package tasks;

/**
 * Class for a task in Duke.
 * Consists of the description of the task and a marker to indicate if it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task.
     * Initializes the description and done marker for the task.
     * Task is initialized as not done by default.
     *
     * @param des the description of the task
     */
    public Task(String des) {
        this.description = des;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return boolean flag indicating whether the task is done or not
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return the string of the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates description to the new description given.
     *
     * @param newDescription updated description
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }



    /**
     * Returns the string representation of the task.
     *
     * @return string consisting of done marker and description of the task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? '1' : '0') + "] " + description;
    }

    /**
     * Clones the task and returns the new copy.
     *
     * @return cloned task with identical description and done status
     */
    @Override
    public abstract Task clone();
}