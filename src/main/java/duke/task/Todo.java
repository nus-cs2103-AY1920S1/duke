package duke.task;

/**
 * A todo item is a task with description. The todo item can be created, marked as done or deleted.
 */
public class Todo extends Task {

    /**
     * Constructs a new todo task with description that has not been done.
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Encodes the todo task to be stored in the storage.
     * @return a string representation of the encoded todo task
     */
    public String encode() {
        return "todo," + super.encode();
    }

    /**
     * Returns a string representation of the todo task. The string representation consist of <code>"[T]"</code>
     * with the string representation of the task.
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
