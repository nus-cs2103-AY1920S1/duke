package duke.task;

public class ToDo extends Task {
    private static final String ABBREV_TASK = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return ABBREV_TASK + " | " + taskIsDoneState + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}