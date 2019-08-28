package duke.task;

import duke.exception.DukeException;

public class Todo extends Task {

    /**
     * Represents a task with description only.
     *
     * @param description The description of the todo item
     * @throws DukeException if the description is empty string
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