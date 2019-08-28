package duke.task;

import duke.exception.DukeException;

public class Todo extends Task {

    /**
     * Constructs a <code>Todo</code> Object to represent a todo.
     *
     * @param description The description of the todo item
     * @throws DukeException when the description is empty
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String serialize() {
        return String.format("T | %d | %s", getStatusCode(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}