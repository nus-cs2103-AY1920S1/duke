package duke.task;

import duke.exception.InvalidDescription;

/**
 * Represents the abstract class of Tasks which are used to represent different
 * tasks.
 */
public abstract class Task {
    enum Priority {
        HIGH, MEDIUM, LOW;
    }

    private String description;
    private boolean done;
    private Priority priority;

    /**
     * Creates a task object with the supplied description. By default, the task is
     * marked as undone.
     * 
     * @param description string representation of the task
     */
    public Task(String description) throws InvalidDescription {
        this.description = description.strip();
        if (this.description.length() == 0) {
            throw new InvalidDescription(this.getClass().getSimpleName());
        }
        this.done = false;
        this.priority = Priority.MEDIUM;
    }

    /**
     * Marks a task as done.
     */
    public void doneTask() {
        this.done = true;
    }

    /**
     * Sets the priority of a task to high.
     */
    public void setHighPriority() {
        this.priority = Priority.HIGH;
    }

    /**
     * Sets the priority of a task to low.
     */
    public void setLowPriority() {
        this.priority = Priority.LOW;
    }

    /**
     * Sets the priority of a task to medium.
     */
    public void setMedPriority() {
        this.priority = Priority.MEDIUM;
    }

    /**
     * Get the priority of a task.
     * 
     * @return the priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Converts the task object to a text format. Allows the object to be saved more
     * easily in a text file.
     * 
     * @return a text representation of the task
     */
    public String toText() {
        String output = "0";
        if (done) {
            output = "1";
        }
        output += "|";

        switch (priority) {
        case LOW:
            output += "L";
            break;
        case MEDIUM:
            output += "M";
            break;
        case HIGH:
            output += "H";
            break;
        default:
            // no default as all cases are taken care of within the enum
        }

        return output + "|" + this.description;
    }

    /**
     * Express the task object in its string representation.
     * 
     * @return a string representing the object
     */
    public String toString() {
        String output = "[X]";
        if (done) {
            output = "[O]";
        }

        switch (priority) {
        case LOW:
            output += "[L]";
            break;
        case MEDIUM:
            output += "[M]";
            break;
        case HIGH:
            output += "[H]";
            break;
        default:
            // no default as all cases are taken care of within the enum
        }
        return output + " " + this.description;
    }
}