package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event that occurs at a specific time. The <code>Evente</code> 
 * class inherits from the <code>Task</code> class.
 */
public class Event extends Task {
    /**
     * A string that represents the time of the event, as inputted by user.
     */
    private String at;

    /**
     * A <code>LocalDateTime</code> object that represents the date and time
     * when the event occurs.
     */
    private LocalDateTime dateTime;

    /**
     * Constructs a <code>Event</code> object. Date and time are parsed and 
     * stored in <code>dateTime</code> field if input is of "dd/MM/yyyy HHmm"
     * format.
     * @param description A string that describes the specific
     *          content of the task.
     * @param at A string that specifies the time of the 
     *          event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            dateTime = LocalDateTime.parse(at, f);
        } catch (DateTimeParseException e) {
        }
    }

    /**
     * Returns a string representatio of the task to be stored in a local file.
     * @return A string in a specific format for clear display in a local file.
     */
    @Override
    public String format() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.at;
    }

    /**
     * Returns a string representation of the task to be printed upon user 
     * inquiry.
     * @return A string representation of the task that displays the type,
     *          description and time of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
