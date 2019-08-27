import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime parsedAt;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.parsedAt = parseDateTime(at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, TaskType.EVENT, isDone);
        this.parsedAt = parseDateTime(at);
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
