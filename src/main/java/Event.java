/**
 * Represents an Event Task
 */
public class Event extends Task {
    private String details;

    Event(String description, String details) {
        super(description);
        this.details = details;
    }

    Event(String description, String details, boolean done) {
        super(description, done);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.details + ")";
    }

    @Override
    String store(){
        return "E|" + getStatus() + "|" + this.description + "|" + this.details;
    }
}