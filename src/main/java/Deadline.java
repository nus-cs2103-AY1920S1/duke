import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime parsedBy;

    public Deadline(String description, String by) {
        super(description);
        this.parsedBy = parseDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (by: " + parsedBy.toString() + ")";
    }
}
