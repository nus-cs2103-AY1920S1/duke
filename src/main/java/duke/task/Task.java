package duke.task;

import java.io.Serializable;

/**
 * An abstract class that represents a human task
 * and keeps track of its completion status.
 * This is the basic Task template that all Task objects
 * must adhere to.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Basic constructor for the Task
     * class that only takes in one string
     * for description.
     *
     * @param description description/name of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a ticked icon (completed) or a
     * crossed icon (uncompleted), depending on the completion
     * status of the Task object.
     *
     * @return string icon depicting a ticked or crossed box.
     */
    public String getStatusIcon() {
        return (isDone
                ? "\u2713"   // return tick symbol
                : "\u2718"); // return x symbol
    }

    /**
     * Marks a Task object as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the Task object, along with
     * its completion status.
     *
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + description;
    }
}