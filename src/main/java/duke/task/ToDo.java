package duke.task;

/**
 * To Do Class. SubClass of Task.
 */
public class ToDo extends Task {
    /**
     * Constructor.
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of a todo task.
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
