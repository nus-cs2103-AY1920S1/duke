package task;

/**
 * Task that has a description of todo.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo Task.
     *
     * @param description Description of todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileFormat() {
        return String.format("T | %s | %s", isDoneString(), getDescription());
    }
}
