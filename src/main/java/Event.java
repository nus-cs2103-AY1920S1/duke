import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent an event that the user wants to attend.
 */
public class Event extends Task {

    protected String at;

    /**
     * Construct a task object that has a time to attend.
     * @param description Description of task
     * @param at Date and Time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getDate() {
        return this.at;
    }

    public LocalDateTime getLocalDateTime() {
        String dateStr = this.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        return dateTime;
    }

    public String getDateTimePrint() {
        LocalDateTime dateTime = this.getLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTimePrint() + ")";
    }
}
