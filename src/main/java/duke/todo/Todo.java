package duke.todo;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getFormattedTask() {
        return "T | " + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + this.getDescription();
    }
}
