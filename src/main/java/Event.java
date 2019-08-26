import java.time.LocalDateTime;

public class Event extends Task {

    protected String at;
    protected LocalDateTime dateTimeAt = null;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setDateTimeAt(LocalDateTime dateTimeAt) {
        this.dateTimeAt = dateTimeAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}