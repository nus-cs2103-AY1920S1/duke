import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Deadline(String s, String by) {
        super(s);
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[D][%s] %s (by: %s)", mark, taskDescription, by.format(formatter).toString());
    }
}
