package task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines the tasks with date and provides it's completion status.
 */
public class Event extends Task {
    private Date at;

    /**
     * Constructs the Event object with specified completion status.
     * @param description Task description.
     * @param isDone Task completion status.
     * @param at Task's meeting date.
     */
    public Event(String description, boolean isDone, Date at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Constructs the Event object with default completion status of FALSE.
     * @param description Task description.
     * @param at Task's meeting date.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats the date for the deadline.
     * @return Formatted date with specified date pattern.
     */
    private String stringDate() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(at);
    }

    /**
     * Returns the literal description of the object.
     * @return Understandable description of object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + stringDate() + ")";
    }

    /**
     * Formats the object so that it can be save into file.
     * @return Formatted description of the object.
     */
    public String toFile() {
        return "E | " + super.getStatusIcon() + " | " + super.getDescription() + " | " + stringDate();
    }
}