package tasks;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    /**
     * Creates a todo object.
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
        super.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}