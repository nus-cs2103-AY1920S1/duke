/**
 * Represents the task to be done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task to be done.
     * @return task to be done
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
