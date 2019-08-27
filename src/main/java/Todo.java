public class Todo extends Task {

    public Todo(String description) {
        super(description, TaskType.TODO);
    }
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    @Override
    public String serialise() {
        return String.format("T | %d | %s\n", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "]" + super.toString();
    }
}

