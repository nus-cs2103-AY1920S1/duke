package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents a type of duke.task.Task object with a fixed time format.
 *
 * @see Task
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructor for event.
     *
     * @param description String representation of the description of event
     * @param at          time in the format of dd/MM/yyyy HHmm
     */
    public Event(String description, String at) throws DateTimeException {
        super(description);
        if (at.length() <= LENGTH_OF_DATE_DESCRIPTION) {
            throw new DateTimeException("Date should be in DD/MM/YYYY HHMM format");
        }
        this.at = LocalDateTime.parse(at, Task.TIME_FORMATTER);
    }

    /**
     * Constructor for event.
     *
     * @param description String representation of the description of event
     * @param at          time in the format of dd/MM/yyyy HHmm
     * @param isDone      boolean value on whether it is done or not
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, Task.TIME_FORMATTER);
    }

    /**
     * Gets the String representation of the event.
     *
     * @return the String representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the String representation of Event object.
     *
     * @return String representation that would be written into data file
     */
    @Override
    public String toDataString() {
        String[] dateTimeSplit = at.toString().split("T");

        String date = dateTimeSplit[0];
        String time = dateTimeSplit[1];

        String[] dateSplit = date.split("-");
        String[] timeSplit = time.split(":");
        String dataAt = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0] + " "
                + timeSplit[0] + timeSplit[1];
        return "E | " + super.toDataString() + " | " + dataAt;
    }
}
