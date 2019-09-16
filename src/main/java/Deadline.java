/**
 * Represents a task categorised as deadline.
 * A deadline object includes a date to specify
 * when the task is due.
 */
public class Deadline extends Task {
    protected DateTime by;

    /**
     * Represents a deadline task.
     * @param description description of task
     * @param by date and time of a deadline task
     */
    public Deadline(String description, DateTime by) {
        super(description);
        assert by != null : "the dateTime of a deadline cannot be empty";
        this.by = by;
    }

    public void changeDateTime(DateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
