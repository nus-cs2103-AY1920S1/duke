public class Todo extends Task {
    String description;
    boolean isDone;

    public Todo(String description) {
        super(description);
    }

    public Todo(String isDone, String description) {
        super(isDone, description);
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription() + "\n";
    }
}
