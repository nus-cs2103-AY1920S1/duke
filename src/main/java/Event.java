import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime eventDateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.eventDateTime = dateTime;
    }

    public Event(String description, boolean isDone, LocalDateTime dateTime) {
        this(description, dateTime);
        this.isDone = isDone;
    }

    /**
     * Getter for when this Event occurs.
     *
     * @return Date and time of this Event.
     */
    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }
}
