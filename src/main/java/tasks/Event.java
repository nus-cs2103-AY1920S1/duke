package tasks;

/**
 * Represents an Event Task. An event task is represented by a description and
 * the timing the event happens.
 */
public class Event extends Task {

    private String timing;

    // Constructor
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + timing + ")";
    }
}