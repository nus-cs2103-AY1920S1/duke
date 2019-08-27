import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DATE_TIME_FORMATTER.format(this.at));
    }
}
