import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Deadline(String s, String by) {
        super(s);
        this.by = LocalDateTime.parse(by, dateTimeFormatter);
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[D][%s] %s (by: %s)", mark, taskDescription, by.format(dateTimeFormatter).toString());
    }
}
