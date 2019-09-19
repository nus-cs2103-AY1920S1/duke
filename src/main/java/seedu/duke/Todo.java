package seedu.duke;

import java.util.Date;

/**
 * Represents a Todo task.
 * A <code>Todo</code> object corresponds to a task with a command that starts with "todo" and contains
 * a description e.g., <code>todo read book</code>
 */
public class Todo extends Task {

    private Date dateTime = null;

    /**
     * Constructor of a Todo class.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Appends the status icon to the description of the Todo object.
     *
     * @return the string representation of the Todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Appends the status icon of the task to the string that will be stored in the data file.
     *
     * @return a string that represents the todo task in the data file
     */
    @Override
    public String writeToFile() {
        if (!isDone) {
            return ("T " + super.writeToFile());
        } else {
            return ("T " + super.writeToFile() + " | " + this.getDoneDateTime());
        }
    }

    /**
     * Return dateTime variable of the task.
     *
     * @return dateTime of the task
     */
    public Date getDateTime() {
        return dateTime;
    }
}
