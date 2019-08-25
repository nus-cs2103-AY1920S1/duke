/**
 * Event class. SubClass of Task.
 */
public class Event extends Task {
    /** Time of event. */
    private String at;
    /** DateTime of the event. */
    private DateTime datetime;

    /**
     * Constructor.
     * @param description Description of event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            datetime = new DateTime(at);
        } catch (InvalidDateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * String representation of the Event.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}
