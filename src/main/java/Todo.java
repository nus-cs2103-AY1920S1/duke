/**
 * Todo Class.
 *
 * Represents the todo-type task.
 *
 * @author Marcus Ong
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}