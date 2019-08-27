import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, Task.TIME_FORMATTER);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, Task.TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + at;
    }
}
