import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the event task given by the user.
 */
public class Event extends Task {
    private LocalDateTime at;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Event(String s, String at) {
        super(s);
        this.at = LocalDateTime.parse(at, formatter);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[E][%s] %s (at: %s)", mark, taskDescription, at.format(formatter).toString());
    }
}
