package duke.task;

import duke.io.DateTime;

/**
 * This abstract Task is the base-class of the other tasks in the task package. It provides the
 * template that all other tasks should extend.
 */
public abstract class Task {
    String description;
    String type;
    DateTime time;
    boolean isDone;

    /** String representations of isDone. */
    static final String IS_DONE = "1";
    static final String IS_NOT_DONE = "0";

    /**
     * Constructs a task with the given description.
     * 
     * @param description of the task
     */
    protected Task(String description, DateTime time) {
        this.description = description;
        this.isDone = false;
        this.type = "DEFAULT";
        this.time = time;
    }

    /**
     * Constructs a task with the given description and truth condition of isDone.
     *
     * @param description of task
     * @param isDone truth condition of the done status of the task
     */
    protected Task(String description, boolean isDone, DateTime time) {
        this.description = description;
        this.isDone = isDone;
        this.type = "DEFAULT";
        this.time = time;
    }

    /**
     * Returns "1" or "0" depending on the truth condition of isDone.
     * 
     * @return "1" or "0" depending on isDone
     */
    String getStatusIcon() {
        return isDone ? IS_DONE : IS_NOT_DONE;
    }

    /**
     * Marks this task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task, for writing to file.
     */
    public abstract String toFileFormattedString();
}
