import java.util.Date;

public class Deadline extends Task {
    private Date by;

    protected Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = Utilities.dateParser(by);
    }
    protected Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        this.by = Utilities.fullDateParser(by);
    }

    @Override
    public String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
