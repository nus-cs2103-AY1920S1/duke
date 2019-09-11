package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public String getTaskTypeLetter() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
