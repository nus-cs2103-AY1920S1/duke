/**
 * The Todo class represents a task of type Todo.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task object.
     *
     * @param description A string representation of the task's description.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Gets the task description and status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}