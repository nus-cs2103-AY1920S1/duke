package duke.task;

/**
 * Task is an abstract class that handles its concrete subclasses,
 * ToDo, Deadline, and Event.
 * A Task can have a status which can be marked as done, a method to
 * get its status icon and description, as well as its String representation.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object (which cannot be initialised on its own).
     * @param description a Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the Task, of whether it has been marked
     * as done or not.
     * @return Returns a "O" representing a Task that is marked done,
     * and a "X" if a Task is not marked as done
     */
    public final String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    /**
     * Marks the Task as done.
     */
    public final void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the Description of the Task Object.
     * @return a description of the current Task
     */
    public final String getDesc() {
        return this.description;
    }

    /**
     * Returns a string representation of the Task object.
     * @return a String representation of the Task object
     */
    @Override
    public abstract String toString();

    /**
     * Returns a string representation of the Task object to be saved
     * into the hard disk file for the Duke program.
     * @return a String Returns a the data representation of the Task
     */
    public abstract String toData();
}
