/**
 * Represents a task that needs to be done before a specific date/time.
 * A <code>Deadline</code> object corresponds to a task represented by
 * two Strings e.g., <code>"submit report","11/10/2019 5pm"</code>
 */
public class Deadline extends Task {
    /** Date/time deadline for this task. */
    protected String by;

    /**
     * Constructs a <code>Deadline</code> with a task description and
     * task deadline.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns condensed description of specified deadline task.
     *
     * @return Condensed description.
     */
    public String formattedString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}