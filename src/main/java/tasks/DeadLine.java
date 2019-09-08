package tasks;

/**
 * Represents a DeadLine Task. A DeadLine object is represented by a description
 * and a time it is due by.
 */
public class DeadLine extends Task {

    protected String by;

    // Constructor
    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}