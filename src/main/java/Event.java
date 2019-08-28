import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    // to manage incoming event list at specific location
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String newAt) {
        this.at = newAt;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + at.format(dateTimeFormatter) + ")";
    }
}