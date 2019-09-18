package duke;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Deadline class creates a Deadline task
 * that extends from the Task class.
 */
public class Deadline extends Task {

    private Date by;

    /**
     * Constructor for class Deadline.
     *
     * @param description The Deadline task.
     * @param by Deadline of the task.
     * @param isPriority The priority level of event.
     */
    public Deadline(String description, Date by, boolean isPriority) {
        super(description, isPriority);
        this.by = by;
    }

    /**
     * Returns the formatted deadline of the task.
     *
     * @return String Deadline of the task.
     */
    public String getBy() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        return dateTimeFormat.format(by);
    }

    /**
     * Overrides the toString method to print the deadline task.
     *
     * @return String Deadline task formatted to string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}