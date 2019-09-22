package task;

/**
 * Handles Deadline type Task with a specified time.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString() + " (by: " + by + ")";
    }
}
