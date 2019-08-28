import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Event(String s, String at) {
        super(s);
        this.at = LocalDateTime.parse(at, dateTimeFormatter);
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[E][%s] %s (at: %s)", mark, taskDescription, at.format(dateTimeFormatter).toString());
    }
}
