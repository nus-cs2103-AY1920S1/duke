/**
 * Represent an event.
 */
public class Event extends Task {
    /**
     * The event timing.
     */
    private String at;

    /**
     * Constructor for Event.
     * @param desc Task description.
     * @param at Event timing.
     */
    Event(String desc, String at) {
        super(desc);
        this.at = at.strip();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
