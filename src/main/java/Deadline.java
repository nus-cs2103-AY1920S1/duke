/**
 * Represents a task categorised as deadline.
 * A deadline object includes a date to specify
 * when the task is due.
 */
public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
