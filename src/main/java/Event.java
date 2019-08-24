import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return eventDateTime.equals(event.eventDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eventDateTime);
    }
}
