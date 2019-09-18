package tasks;

/**
 * Represents a todo.
 *
 * @author Michelle Yong
 */
public class Todo extends Task {
    /**
     * Creates a todo with description.
     *
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