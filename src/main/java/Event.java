/**
 * Event task of Duke.
 */
public class Event extends Task {

    protected String at;

    /**
     * Event constructor with description and at as string.
     * @param description description of tasks
     * @param at refers to location/venue/places
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}