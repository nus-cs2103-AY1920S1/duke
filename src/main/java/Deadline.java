import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    public Date getByDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }

    @Override
    public String toStorageString() {
        return "D`" + super.toStorageString() + '`' + by.toString();
    }
}
