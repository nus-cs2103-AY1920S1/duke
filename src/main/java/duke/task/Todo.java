package duke.task;

/**
 * Represents a todo task.
 * An <code>Todo</code> object corresponds to a type of <code>Task</code> object with a task to be completed.
 */
public class Todo extends Task {

    /**
     * Constructor for <code>Todo</code>.
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of task that this <code>Event</code> object is.
     * @return Todo type.
     */
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
