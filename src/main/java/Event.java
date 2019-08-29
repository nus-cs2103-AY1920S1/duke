import java.util.Date;

public class Event extends Task {
    protected Date time;

    public Event(String description, Date time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + this.time + ")\n";
    }
}
