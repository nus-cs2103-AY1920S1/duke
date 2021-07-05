package duke.task;

/**
 * Represents a task to do.
 */
public class Todo extends Task {

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
