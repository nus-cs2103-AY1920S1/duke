package duke.task;

public class Todo extends Task {
    /**
     * Constructs a new todo.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates the todo's string representation.
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
