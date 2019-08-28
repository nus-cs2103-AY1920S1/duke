import java.util.Date;

public class Event extends Task {
    private Date timeframe;

    public Event(String description, Date timeframe) {
        super(description);
        this.timeframe = timeframe;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + timeframe + ")";
    }
}
