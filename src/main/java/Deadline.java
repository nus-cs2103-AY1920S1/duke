import java.util.Date;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, String by) {
        super(description);
        assert (!by.isEmpty());
        this.by = Task.parseDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.stringifyDate(by) + ")";
    }
}
