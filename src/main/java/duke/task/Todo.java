package duke.task;

/**
 * To-do class to create to-do task.
 *
 * @author TeoShyanJie
 *
 */
public class Todo extends Task {
    /**
     * Constructor of To-do class.
     * @param description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * To String method of To-do class.
     * @return String of description with type.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}