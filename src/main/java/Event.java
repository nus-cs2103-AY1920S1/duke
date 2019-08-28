import java.time.LocalDateTime;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
        this.pattern = LocalDateTime.parse(at.trim(), dateTimeFormatter);
    }

    public Event(String description, String at, String isDone) {
        super(description, isDone);
        this.pattern = LocalDateTime.parse(at.trim(), dateTimeFormatter);
        this.at = at;
    }
    @Override
    public String getFormatToFile() {
        return String.format("E | %d | %s | %s \n", (isDone ? 1 : 0), description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}