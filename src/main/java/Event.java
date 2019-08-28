import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + at.format(myFormatter) + ")";
    }
}