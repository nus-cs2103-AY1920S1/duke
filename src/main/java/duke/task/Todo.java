package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task with description only.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object given a description.
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
    public Task copy() {
        try {
            Task copy = new Todo(getDescription());
            if (this.getIsDone()) {
                copy.markAsDone();
            }
            return copy;
        } catch (DukeException e) {
            return null;
        }
    }

    /**
     * Converts a todo into encoded form (e.g. 'T | 0 |  join sports club').
     */
    @Override
    public String encode() {
        return String.format("T | %d | %s", getStatusCode(), getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}