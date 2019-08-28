import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task given by the user.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Deadline(String s, String by) {
        super(s);
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[D][%s] %s (by: %s)", mark, taskDescription, by.format(formatter).toString());
    }
}
