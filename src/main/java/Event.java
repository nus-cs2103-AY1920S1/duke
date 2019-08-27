import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.toString() + " (at: " + DateTimeHelper.formatOutput(eventTime) + ")";
    }
}
