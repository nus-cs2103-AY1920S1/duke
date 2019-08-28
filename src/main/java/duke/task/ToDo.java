package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String format() {
        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
