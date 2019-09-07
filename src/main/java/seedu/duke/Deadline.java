package seedu.duke;

import java.util.Date;

/**
 * Represents a Deadline task.
 * A <code>Deadline</code> object corresponds to a task with a command that starts with "deadline" and contains
 * a description and a timestamp e.g., <code>deadline project 3/12/2022 0212</code>
 */
public class Deadline extends Task {

    private Date dateTime;

    /**
     * Constructor of Deadline task.
     *
     * @param description the description of the deadline task
     * @param dateTime the date and time of the event in the format dd/MM/yyyy HHmm
     */
    public Deadline(String description, Date dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Appends the status icon to the description and formats the date and time.
     *
     * @return the String representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + PRINT_FORMAT.format(dateTime) + ")";
    }

    /**
     * Appends the status icon to the description and formats the date and time to be stored
     * in the datafile.
     *
     * @return the String representation of the Deadline object to be stored in datafile
     */
    @Override
    public String writeToFile() {
        return ("D " + super.writeToFile() + " | " + DATE_FORMAT.format(dateTime));
    }

}

