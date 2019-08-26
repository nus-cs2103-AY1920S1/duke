import java.util.Date;

public class Deadline extends Task {

    private Date by;

    public Deadline(String description, Date by) throws DukeException {
        super(description);
        if (by.equals("")) {
            throw new DukeException("The date/time of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.by = by;
    }

    protected String getTypeNameWithQuantifier() {
        return "a deadline";
    }

    protected String toExportFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.formatDate(by) + ")";
    }
}
