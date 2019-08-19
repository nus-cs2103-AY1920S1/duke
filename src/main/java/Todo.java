/**
 * Todo Class.
 *
 * Represents the todo-type task.
 *
 * @author Marcus Ong
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}