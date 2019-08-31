import java.util.Date;
/**
 * Represents an Event Task
 */
public class Event extends Task {
    private Date details;

    Event(String description, Date details) {
        super(description);
        this.details = details;
    }

    Event(String description, Date details, boolean done) {
        super(description, done);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + details + ")";
    }

    @Override
    String store(){
        return "E|" + getStatus() + "|" + description + "|" + details;
    }
}