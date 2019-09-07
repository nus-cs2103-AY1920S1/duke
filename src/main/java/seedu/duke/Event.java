package seedu.duke;

import java.util.Date;

/**
 * Represents a Event task.
 * A <code>Event</code> object corresponds to a task with a command that starts with "event" and contains
 * a description and a timestamp e.g., <code>event music festival 3/12/2022 0212</code>
 */
public class Event extends Task {

    private Date dateTime;

    /**
     * Constructor for Event class.
     *
     * @param description the description of the event
     * @param dateTime the date and time of the event in the format dd/MM/yyyy HHmm
     */
    public Event(String description, Date dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Appends the status icon to the description and formats the date and time.
     *
     * @return the String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + PRINT_FORMAT.format(dateTime) + ")";
    }

    /**
     * Appends the status icon to the description and formats the date and time to be stored
     * in the datafile.
     *
     * @return the String representation of the Event object to be stored in datafile
     */
    @Override
    public String writeToFile() {
        return ("E " + super.writeToFile() + " | " + DATE_FORMAT.format(dateTime));
    }

}
