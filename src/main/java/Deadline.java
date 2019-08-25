/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * Stores deadline of task.
     */
    private String by;

    /**
     * Constructor for Deadlne.
     * @param desc Task description.
     * @param by Deadline of task.
     */
    Deadline(String desc, String by) {
        super(desc);
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
