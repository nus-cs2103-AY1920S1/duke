/**
 * Represents a To-do Task
 */
public class Todo extends Task {
    private String details;

    Todo(String description) {
        super(description);
    }

    Todo(String description, String details) {
        super(description);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }
}