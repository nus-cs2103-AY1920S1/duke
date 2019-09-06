/**
 * Represents a deadline object.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "deadline";
        this.symbol = "D";
    }

    public String getDateString() {
        return this.by;
    }

    @Override
    public String toString() {
        return this.getSymbol() + this.getStatusIcon() + this.getDescription() + " (by: " + this.by + ")";
    }
}