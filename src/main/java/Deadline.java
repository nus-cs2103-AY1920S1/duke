import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = DukeDateTimeParser.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DukeDateTimeFormatter.format(by) + ")";
    }
}
