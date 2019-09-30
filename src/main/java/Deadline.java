/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to
 * with a valid task creation command e.g., <code>deadline homework /by 14/09/2019 0830</code>
 */

public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
        super.changeToFileFormat('D', description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
