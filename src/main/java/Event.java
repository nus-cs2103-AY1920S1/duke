import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private LocalDateTime at;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Event(String s, String at) {
        super(s);
        this.at = LocalDateTime.parse(at, formatter);
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[E][%s] %s (at: %s)", mark, taskDescription, at.format(formatter).toString());
    }
}
