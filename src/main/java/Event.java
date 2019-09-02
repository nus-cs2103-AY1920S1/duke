import java.util.Date;

public class Event extends Task {
    protected Date at;

    /**
     * Represents the upcoming event the user wants to add to his tasklist.
     * @param description refers to the details of the event
     * @param at refers to the date when the event is happening
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event date.
     * @return Date at
     */
    public Date getAt() {
        return at;
    }

    /**
     * Returns the stringified form the Event task.
     * @return String event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}