import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = Parser.parseDateTime(by);
    }

    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + Formatter.standardFormatDateTime(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Formatter.prettyFormatDateTime(by) + ")";
    }
}
