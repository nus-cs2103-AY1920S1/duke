package tasks;

/**
 * Task is an abstract class that represents the
 * type of tasks that Duke can perform operations on.
 * These Tasks are one of 3 types: ToDo, Deadline or Event task.
 */
public abstract class Task {
    /** The description of the task */
    protected String description;
    /** Boolean representing the state of the task, if it is completed */
    protected boolean isDone;

    /**
     * Task constructor that takes a description of the task
     * and its state as arguments.
     *
     * @param description The description of the task.
     * @param isDone the state of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon associated with this task.
     * A tick if it is done, an X symbol if it is not.
     *
     * @return String status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the state of the task from not completed to completed.
     * Boolean value is held by isDone is changed from false to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string that is of the appropriate format
     * to be saved to the file. This formatting ensures that
     * the task information can be read accurately again in the future.
     *
     * @return String format of the task to be saved to the file.
     */
    public String fileString() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

}