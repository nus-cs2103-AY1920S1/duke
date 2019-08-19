public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return num + ".[T][" + super.getStatusIcon() + "] " + description;
    }

    public String done() {
        return "  [T][✓] " + description;
    }

}
