import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime parsedBy;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.parsedBy = parseDateTime(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, TaskType.DEADLINE, isDone);
        this.parsedBy = parseDateTime(by);
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
