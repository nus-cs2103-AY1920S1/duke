import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent an event that the user wants to attend.
 */
public class Event extends TaskWithDateTime {

    /**
     * Construct a task object that has a time to attend.
     * @param description Description of task
     * @param dateTime Date and Time of the event
     */
    public Event(String description, String dateTime) {
        super(description, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTimePrint() + ")";
    }
}
