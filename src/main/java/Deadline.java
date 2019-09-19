import java.util.Date;

public class Deadline extends Task {
    private Date by;

    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    Date getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}