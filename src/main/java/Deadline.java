/**
 * Deadline Class.
 *
 * Represents the deadline-type task.
 *
 * @author Marcus Ong
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getTag() + "]" + super.toString() + " (by: " + by + ")";
    }
}