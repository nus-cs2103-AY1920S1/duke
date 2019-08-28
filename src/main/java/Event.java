import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * A subclass of Task that categorized itself as an occasion that will
     * be held at a particular time.
     *
     * @param description description of the Event (Task).
     * @param at the date of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    // get the date and time with specific format
    public String getAt() {
        return at.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + at.format(dateTimeFormatter) + ")";
    }
}