/**
 * Deadline methods,extends Task.
 */
import java.time.LocalDateTime;
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime time = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public void setDateTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    public String toStringintxt() {
        return "D," + super.toStringintxt() + "," + by;
    }
}
