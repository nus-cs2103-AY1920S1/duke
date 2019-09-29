package q.task;

/**
 * A Todo Task. Has a description.
 */
public class Todo extends Task {
    /**
     * Constructs an undone todo with a description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo with a description and a doneness.
     *
     * @param description The description of the todo
     * @param isDone      The doneness of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the task to a string, including the doneness of the todo and its description.
     *
     * @return A string representing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo to an encoded string for saving.
     *
     * @return A string encoding the todo for saving.
     */
    public String toSave() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }

}
