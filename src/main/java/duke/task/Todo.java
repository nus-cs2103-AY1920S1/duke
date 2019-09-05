package duke.task;

import duke.task.DukeException;
import duke.task.Task;

public class Todo extends Task {
    /**
     * The constructor for this class.
     * @param description The description of the task.
     * @throws DukeException If the description is missing.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
