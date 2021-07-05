package dukepkg;

/**
 * The task of type Todo.
 */
public class Todo extends dukepkg.Task {
    /**
     * Instantiates a new Todo Task.
     *
     * @param task the content used to construct the task.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
