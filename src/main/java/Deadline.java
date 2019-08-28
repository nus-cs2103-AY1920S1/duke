import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime parsedBy;

    public Deadline(String description, LocalDateTime parsedBy) {
        super(description, TaskType.DEADLINE);
        this.parsedBy = parsedBy;
    }

    public Deadline(String description, boolean isDone, LocalDateTime parsedBy) {
        super(description, TaskType.DEADLINE, isDone);
        this.parsedBy = parsedBy;
    }



    @Override
    public String serialise() {
        return String.format("D | %d | %s | %s\n", isDone ? 1 : 0, description, parsedBy.toString());
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (by: " + parsedBy.toString() + ")";
    }
}
