import java.util.Date;

/**
 * Represents a event.
 *
 * @author Michelle Yong
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates a event with specified description and date.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     */
    public Event(String description, Date at) {
        super(description);
        super.date = at;
        super.type = "E";
    }

    @Override
    public String toString() {
        String deadlineDate = super.date.toString().substring(0, 16);
        return "[E]" + super.toString() + " (at: " + deadlineDate + ")";
    }
}