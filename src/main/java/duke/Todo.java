package duke;

import java.io.Serializable;

/**
 * This class represents a To-do Task.
 */
public class Todo extends Task implements Serializable {

    /**
     * Returns a to-do Task instance.
     * @param description description of the Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the Task.
     * @return a String representation of the Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
