package duke.task;

import duke.exception.DukeException;

/**
 * Represents the todo object.
 */
public class Todo extends Task {

    /**
     * Creates a todo object with the supplied description.
     * 
     * @param description string representation of the task
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Converts the todo object to a text format. Allows the object to be saved more
     * easily in a text file.
     * 
     * @return a text representation of the todo object
     */
    public String toText() {
        return "T|" + super.toText();
    }

    /**
     * Express the todo object in its string representation.
     * 
     * @return a string representing the object
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}