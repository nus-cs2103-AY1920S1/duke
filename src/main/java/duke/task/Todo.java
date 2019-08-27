package duke.task;

/**
 * Represents a <code>Task</code> that has to be done. A <code>Event</code> object corresponds to a description about
 * the <code>Task</code>.
 */
public class Todo extends Task {
    /**
     * Constructor for <code>Todo</code>.
     * @param description Details about the <code>Todo</code> task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the first letter of the task type.
     * @return "T".
     */
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
