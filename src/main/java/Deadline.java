import java.util.Date;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = parseDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
