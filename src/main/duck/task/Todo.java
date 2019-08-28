package duck.task;

/**
 * This is a one kind of <code>Task</code> to specify the details for the item in the task list.
 */
public class Todo extends Task {

    /**
     * {@inheritDoc}
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     * @return a string representing this todo in the format of a tag "[T]" at the start, followed by the description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * Compares two <code>Todo</code> objects by their descriptions and <code>isDone</code> status.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Task;
    }
}
