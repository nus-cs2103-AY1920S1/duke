package seedu.duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Represents a task.
 * A <code>Task</code> object corresponds to a task with a command that starts
 * with "todo", "event" or "deadline", followed by description.
 *
 */
public class Task {

    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static final DateFormat PRINT_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH.mm aa");
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
    public String markAsDone() {
        this.isDone = true;
        this.doneIcon = 1;
        return ("Nice! I've marked this task as done:\n"
                + this) + "\n";
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
