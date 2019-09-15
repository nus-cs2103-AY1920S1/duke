package duke.task;

/**
 * Represents a type of duke.task.Task object.
 *
 * @see Task
 */

public class Todo extends Task {
    /**
     * Constructor for duke.task.Todo.
     *
     * @param description String representation of the user input
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Another constructor for duke.task.Todo
     *
     * @param description String representation of the description of Todo
     * @param isDone      boolean value on whether it is done or not
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the String representation of the todo.
     *
     * @return the String representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of Event object.
     *
     * @return String representation that would be written into data file
     */
    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }
}
