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

    @Override
    public String toString() {
        return String.format("[%s]%s", TaskType.TODO.getTag(), super.toString());
    }
}