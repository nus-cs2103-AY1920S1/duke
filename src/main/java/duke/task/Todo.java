package duke.task;

/**
 * Todo task that inherits from Task.
 */
public class Todo extends Task {
    String task;

    /**
     * Constructs a new Todo task.
     *
     * @param input user input.
     */
    public Todo(String input) {
        super(input);
    }

    /**
     * Returns a string representation of the Todo object. Example: [T][✗] borrow book.
     *
     * @return a string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
