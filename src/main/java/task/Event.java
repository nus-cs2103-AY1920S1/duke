package task;

/**
 * Task which has a place.
 *
 */
public class Event extends Task {
    private String at;

    /**
     * Event constructor.
     *
     * @param desc Description containing description and venue of event.
     * @param at Place of the event.
     */
    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String fileFormat() {
        return String.format("E | %s | %s | %s", isDoneString(), getDescription(), this.at);
    }
}
