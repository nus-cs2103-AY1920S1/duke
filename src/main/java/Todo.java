public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + description;
    }

    public String done() {
        return "  [T][✓] " + description;
    }

}
