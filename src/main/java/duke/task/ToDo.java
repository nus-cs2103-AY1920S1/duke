package duke.task;

public class ToDo extends Task {
    private String by;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
