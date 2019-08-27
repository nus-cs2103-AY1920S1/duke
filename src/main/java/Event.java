import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime parsedAt;

    public Event(String description, String at) {
        super(description);
        this.parsedAt = parseDateTime(at);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (at: " + parsedAt.toString() + ")";
    }

}
