/**
 * Represents an Event Task
 */
public class Event extends Task {
    private String details;

    Event(String description, String details) {
        super(description);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.details + ")";
    }
}