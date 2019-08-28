import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.at = parseTime(at.trim());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
