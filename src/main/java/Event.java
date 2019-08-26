import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }
    @Override
    public String getSimplifiedRepresentation() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.taskName + " | "
                + DateTimeFormatter.ofPattern("d/M/yyyy HHmm").format(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + (this.at.getDayOfMonth() % 10 == 1
                ? DateTimeFormatter.ofPattern("d'st of' MMMM yyyy, h.mma").format(this.at)
                : this.at.getDayOfMonth() % 10 == 2
                    ? DateTimeFormatter.ofPattern("d'nd of' MMMM yyyy, h.mma").format(this.at)
                    : this.at.getDayOfMonth() % 10 == 3
                        ? DateTimeFormatter.ofPattern("d'rd of' MMMM yyyy, h.mma").format(this.at)
                        : DateTimeFormatter.ofPattern("d'th of' MMMM yyyy, h.mma").format(this.at)) + ")";
    }
}
