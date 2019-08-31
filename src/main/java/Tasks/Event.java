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

    /**
     * Returns the event date as a String as per user input.
     * @return event date as a string
     */
    public String getEventDateString() {
        return eventDateString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateString + ")";
    }
}