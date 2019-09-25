package task;

/**
 * Extends from Task, a task with only a name and no other special field.
 */
public class ToDo extends Task {

    /**
     * Constructor for a todo, defaults isDone to false (Marks the new todo as undone).
     * @param description this todo's name
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for a todo when reading from state file.
     * @param description this todo's name
     * @param isDone boolean true or false if this todo is done or undone respectively
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns String representation of this todo.
     * @return String in format [T] (super class Task's toString)
     */
    @Override
    public String toString() {
        return "[ToDo] " + super.toString();
    }
}