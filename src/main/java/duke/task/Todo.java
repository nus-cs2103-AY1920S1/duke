package duke.task;

/**
 * Represents a todo object.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo object to a String representing it for storage purposes.
     * @return
     */
    @Override
    public String toStorage() {
        int isDoneInt = isDone ? 1 : 0;
        return String.format("%d | T | %s", isDoneInt, description);
    }
}
