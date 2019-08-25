/**
 * Event class. SubClass of Task.
 */
public class Event extends Task {
    /** Time of event. */
    private String at;

    /**
     * Constructor.
     * @param description Description of event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the timing of the event.
     * @return String time;
     */
    public String getAt() {
        return at;
    }

    /**
     * String representation of the Event.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
