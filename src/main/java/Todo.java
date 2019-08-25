public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    Todo (String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFile() {
        return "T | " + super.getStatusIcon() + " | " + description;
    }
}