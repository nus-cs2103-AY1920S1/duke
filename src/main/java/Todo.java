/**
 * Represents a todo task which is a subclass of Task class.
 * A todo needs a description.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}