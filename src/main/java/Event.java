/**
 * Represents a Task of the specific type Event.
 * Event has a time period, specified with /at (Eg "event read book /at 3pm-5pm)"
 */
public class Event extends Task {
    private String duration;

    /**
     * Creates an Event with the given description and duration.
     * @param description of the task.
     * @param duration of the task.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + "(at:" + this.duration + ")" + super.formatTags());
    }
}