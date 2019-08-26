package duke.task;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    protected String getTypeNameWithQuantifier() {
        return "a todo";
    }

    public String toExportFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
