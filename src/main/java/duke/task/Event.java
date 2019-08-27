package duke.task;

/**
 * Represents an Task object of type Event. An <code>Event</code> object
 * specifies when the event is and its item description.
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "E " + super.toString() + "| " + at;
    }
}
