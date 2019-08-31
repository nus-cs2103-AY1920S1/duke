package seedu.duke;

/**
 * Represents a task.
 * A <code>Task</code> object corresponds to a task with a command that starts
 * with "todo", "event" or "deadline", followed by description.
 *
 */
public class Task {
    private String description;
    boolean isDone;
    int doneIcon;

    /**
     * Constructor of the Task class.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        doneIcon = 0;
    }

    /**
     * Return the status icon depending on whether the task is marked done or not.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    /**
     * Sets the isDone variable to true and the doneIcon to 1.
     * Prints the done message.
     */
    public void markAsDone() {
        this.isDone = true;
        this.doneIcon = 1;
        System.out.println("Nice! I've marked this task as done:\n"
                + this);
    }

    /**
     * Sets the isDone variable to true and the doneIcon to 1.
     */
    public void updateDone() {
        this.isDone = true;
        this.doneIcon = 1;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Appends the status icon with the description of the task.
     *
     * @return the string description of the task
     */
    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }

    /**
     * Appends the done icon with the description of the task.
     *
     * @return the string description of the task in the format to be written in the datafile
     */
    public String writeToFile() {
        return ("| " + doneIcon + " | " + this.description);
    }

}
