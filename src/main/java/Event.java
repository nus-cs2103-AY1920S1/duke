import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime parsedAt;

    public Event(String description, LocalDateTime parsedAt) {
        super(description, TaskType.EVENT);
        this.parsedAt = parsedAt;
    }

    public Event(String description, boolean isDone, LocalDateTime parsedAt) {
        super(description, TaskType.EVENT, isDone);
        this.parsedAt = parsedAt;
    }

    @Override
    public String serialise() {
        return String.format("E | %d | %s | %s\n", isDone ? 1 : 0, description, parsedAt.toString());
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (at: " + parsedAt.toString() + ")";
    }

}
