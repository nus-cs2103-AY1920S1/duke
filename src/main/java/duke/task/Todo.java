package duke.task;

import duke.task.Task;

public class Todo extends Task {
    private String type;

    /**
     * Creates a Todo task.
     *
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Represents the task in a format suitable for the user to read.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
