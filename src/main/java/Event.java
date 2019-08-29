import java.time.LocalDateTime;

/**
 * Represents a Task with a time of event attached
 */

public class Event extends Task {
    private String time;
    private LocalDateTime localDateTime;

    public Event(String name, boolean isDone, String time, LocalDateTime localDateTime) {
        super(name, isDone);
        this.time = time;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getName() + " (at: " + localDateTime.toString()  + ")";
    }
}
