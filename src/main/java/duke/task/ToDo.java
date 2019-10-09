package duke.task;

public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param description Description of ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
