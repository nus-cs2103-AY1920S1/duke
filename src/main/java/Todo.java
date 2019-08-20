/**
 * Todo Class.
 *
 * Represents the todo-type task.
 *
 * @author Marcus Ong
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getTag() + "]" + super.toString();
    }
}