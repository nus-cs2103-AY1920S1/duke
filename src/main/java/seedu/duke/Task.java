package seedu.duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task.
 * A <code>Task</code> object corresponds to a task with a command that starts
 * with "todo", "event" or "deadline", followed by description.
 *
 */
public class Task implements Comparable<Task> {

    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static final DateFormat PRINT_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH.mm aa");
    private String description;
    boolean isDone;
    int doneIcon;
    private Date dateTime;

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

    /**
     * Return dateTime variable of the task.
     *
     * @return dateTime of the task
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Compare the deadline and event tasks by dateTime. Else, compare using the description of the task
     *
     * @param o the task to compare with
     * @return an integer
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Todo && this instanceof Todo) {
            return this.getDescription().compareTo(o.getDescription());
        } else if (!(o instanceof Todo) && !(this instanceof Todo)) {
            return this.getDateTime().compareTo(o.getDateTime());
        } else if (this instanceof Todo && !(o instanceof Todo)) {
            return 10;
        }  else if (!(this instanceof Todo) && (o instanceof Todo)) {
            return -10;
        } else {
            return this.getDescription().compareTo(o.getDescription());
        }
    }




/*

        if (this.getDateTime() == null && o.getDateTime() == null) { // both are instance of Todo
            return this.getDescription().compareTo(o.getDescription());
        } else if (!(o instanceof Todo) && !(this instanceof Todo)) {
            return this.getDateTime().compareTo(o.getDateTime());
        } else if (o.getDateTime() == null) {
            return (int) Double.MIN_VALUE;
        } else if (this.getDateTime() == null) {
            return (int) Double.MAX_VALUE;
        } else {
            return this.getDescription().compareTo(o.getDescription());
        }
    }
*/
}