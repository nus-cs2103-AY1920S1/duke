import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.TIME_FORMATTER);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, Task.TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by;
    }
}
