package duke.task;

import java.util.Date;

/**
 * Task containing information about a todo.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo object.
     * 
     * @param description Description of todo.
     */
    public Todo(String description) {
        super("T", description, new Date());
    }

    /**
     * Returns String containing information about the todo.
     * 
     * @return String containing status, description and date of todo.
     */
    public String toString() {
        return String.format("[%s][%s] %s", this.type,
                this.getStatusIcon(),
                this.description);
    }
}
