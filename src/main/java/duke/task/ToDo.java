package duke.task;

/**
 * Creates a ToDo Task. Description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toWriteFile() {
        return "T | " + getDoneStatus() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
