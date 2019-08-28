import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(boolean done, String description, String at) throws DateTimeParseException {
        super(description);
        this.at = parseTime(at.trim());
        this.isDone = done;
    }

    public Event(String description, String at) throws DateTimeParseException {
        this(false, description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String saveFormat() {
        return String.format("E | %d | %s | %s\n", isDone ? 1 : 0, getDesc(), at);
    }
}
