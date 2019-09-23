package duke.task;

import duke.execution.DukeException;

/**
 * Represents Todo object, subclass of Task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toFileString(){
        StringBuilder fileString = new StringBuilder();
        fileString.append("T | 0 | " + description);
        return fileString.toString();
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T]" + "[" + "\u2713" + "]" + this.description;
        } else {
            return "[T]" + "[" + "\u2718" + "]" + this.description;
        }
    }

    public Task updateDateTime(String info) throws DukeException{
        throw new DukeException("Task is a Todo. There is no associated date/time.");
    }
}
