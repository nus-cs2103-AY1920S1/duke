import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s(at: %s)", done ? "v" : "x", name, dateTime.format(date).toString());
    }
}
