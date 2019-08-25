/**
 * Represent a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param desc Task description.
     */
    Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
