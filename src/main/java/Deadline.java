import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(boolean done, String description, String by) throws DateTimeParseException {
        super(description);
        this.by = parseTime(by.trim());
        this.isDone = done;
    }

    public Deadline(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveFormat() {
        return String.format(" D | %d | %s | %s\n", isDone ? 1 : 0, getDesc(), by);
    }
}
