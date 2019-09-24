import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event which is a subclass of Task class.
 * An event needs the LocalDateTime to specify when the event is happening.
 */
public class Event extends Task {

    private LocalDateTime at;

    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    public String getTaskAt() {
        return this.at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + at.format(myFormatter) + ")";
    }
}