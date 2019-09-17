package duke.task;

import duke.task.Task;

public class Todo extends Task {

    protected String at;

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
