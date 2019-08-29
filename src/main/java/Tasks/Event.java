package Tasks;

import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime eventDate;
    protected String eventDateString;

    public Event(String description, LocalDateTime eventDate, String eventDateString) {
        super(description);
        this.eventDate = eventDate;
        this.eventDateString = eventDateString;
    }

    public String getEventDateString() {
        return eventDateString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateString + ")";
    }
}