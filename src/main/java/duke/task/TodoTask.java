package duke.task;

/**
 * A todo task in the Duke Program.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageFormat() {
        return String.join(" | ", new String[] { "T", getIsDone() ? "1" : "0", super.description });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
